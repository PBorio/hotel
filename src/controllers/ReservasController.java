package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import repositorios.CategoriaRepositorio;
import repositorios.PoliticaPrecoRepositorio;
import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.validators.HospedeValidation;
import controllers.validators.ReservaValidation;
import controllers.views.reservas.DetalhesDosParametros;
import controllers.views.reservas.InformativoDeQuartos;
import controllers.views.reservas.ParametrosReserva;
import controllers.views.reservas.ReservasView;
import domain.AgrupadorReservas;
import domain.Hospede;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.CalculoDeValorDaDiariaService;
import domain.servicos.InformativoService;
import domain.servicos.ServicoDeReserva;
import domain.servicos.interfaces.HospedeService;

@Resource
public class ReservasController {
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private ReservaRepositorio reservaRepositorio;
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Validator validator;
	private ReservasView reservasView;
	private HospedeService hospedeService;

	public ReservasController(Result result, 
			                  CategoriaRepositorio categoriaRepositorio, 
			                  QuartoRepositorio quartoRepositorio, 
			                  HospedeService hospedeService,
			                  ReservaRepositorio reservaRepositorio,
			                  PoliticaPrecoRepositorio politicaPrecoRepositorio,
			                  Validator validator,
			                  ReservasView reservasView){
		this.result = result;
		this.categoriaRepositorio = categoriaRepositorio;
		this.quartoRepositorio = quartoRepositorio;
		this.hospedeService = hospedeService;
		this.reservaRepositorio = reservaRepositorio;
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.validator = validator;
		this.reservasView = reservasView;
	}
	
	@Get
	@Path("/reservas/")
	public void parametrosIniciais(){
		reservasView.clear();
	}
	
	public void reservar(Quarto quarto){
		
		try{
			quarto = quartoRepositorio.buscaPorId(quarto.getId());
			DetalhesDosParametros detalhe = reservasView.getParametrosReserva().primeiroDetalheSemQuarto();
			
			if (detalhe == null)
				throw new HotelException("Erro: Não foi possível alterar o quarto da reserva");
			
			if (reservasView.ehParaUmQuartoSo())
				reservasView.getReservas().clear();
			
			detalhe.setQuarto(quarto);
			Reserva reserva = new Reserva();
			reserva.setQuarto(quarto);
			reserva.setNumeroAdultos(detalhe.getNumeroAdultos());
			reserva.setNumeroCriancas0a5(detalhe.getNumeroCriancas0a5());
			reserva.setNumeroCriancas6a16(detalhe.getNumeroCriancas6a16());
			reserva.setNumeroCriancas17a18(detalhe.getNumeroCriancas17a18());
			reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
			reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
			
			if (quarto.possuiReservasNoMesmoPeriodo(reserva))
				throw new HotelException("Já existe reserva para o quarto "+quarto.getNumero()+" para este período.");
			
			CalculoDeValorDaDiariaService servicoPrecos = new CalculoDeValorDaDiariaService(politicaPrecoRepositorio.buscaTodos());
			servicoPrecos.calcularEInformarValorNaReserva(reserva);
			
			reservasView.addReserva(reserva);
			
			if (reservasView.precisaDeMaisQuartos()){
				List<InformativoDeQuartos> quartosParaReserva = buscarQuartosDisponiveis(detalhe);
				result.include("quartoList", quartosParaReserva);
				result.redirectTo(this).parametrosDetalhes(reservasView.getParametrosReserva());
			}
			else{
				result.of(this).showReserva();
			}
			
		}catch(HotelException e){
			result.include("reserva",reservasView);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).parametrosIniciais();
		}
	}
	
	public void showReserva() {}

	public void confirmar(Hospede hospede){
		
		try{
			HospedeValidation validation = new HospedeValidation(validator);
			validator = validation.validarHospede(hospede);
			
			if (validator.hasErrors()){
				result.include("reserva",reservasView);
				result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			    validator.onErrorUsePageOf(this).showReserva();
			}

			Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede);
			
			AgrupadorReservas agrupadorReservas = new AgrupadorReservas();
			for (Reserva reserva : reservasView.getReservas()){
				Quarto quarto = reserva.getQuarto();
				if (quarto.possuiReservasNoMesmoPeriodo(reserva))
					throw new HotelException("Já existe reserva para o quarto "+quarto.getNumero()+" para este período.");
				reserva.setHospede(hospedeExistente);
				agrupadorReservas.addReserva(reserva);
			}
			
			reservaRepositorio.salvarReservas(agrupadorReservas);
			result.redirectTo(ConsultasController.class).consulta();
			
		}catch(HotelException e){
			result.include("reserva",reservasView);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).showReserva();
		}
	}

	@Get
	@Path("/reservas/cancelar/{id}")
	public void cancelar(Long id){
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		reserva.cancelar();
		reservaRepositorio.atualiza(reserva);
		result.redirectTo(ConsultasController.class).consulta();
	}
	
	@Post
	@Path("/reservas/atualizar/valores")
	public void atualizarValores(List<Reserva> reservas){
		for (Reserva r : reservas){
			for (Reserva jaReservada : this.reservasView.getReservas()){
				if (r.getValorReserva() == null || r.getValorReserva().doubleValue() == 0)
					continue;
				
				if (!r.getQuarto().equals(jaReservada.getQuarto()))
					continue;
				
				if (r.getValorReserva().doubleValue() != jaReservada.getValorReserva().doubleValue())
					jaReservada.setValorReserva(r.getValorReserva());
			}
		}
		result.of(this).showReserva();
	}
	
	public void parametrosDetalhes(ParametrosReserva parametrosReserva){
		
		reservasView.setChegada(parametrosReserva.getChegada());
		reservasView.setSaida(parametrosReserva.getSaida());
		reservasView.setNumeroDeQuartos(parametrosReserva.getNumeroDeQuartos());
		reservasView.setParametrosReserva(parametrosReserva);
		
		ReservaValidation validation = new ReservaValidation(validator, reservasView);
		validator = validation.validarBusca();
		
		if (validator.hasErrors()){
			result.include("parametrosReserva", parametrosReserva);
		    validator.onErrorUsePageOf(this).parametrosIniciais();
		}
		
		DetalhesDosParametros detalhesDosParametros = reservasView.getParametrosReserva().primeiroDetalheSemQuarto();
		List<InformativoDeQuartos> quartosParaReserva = buscarQuartosDisponiveis(detalhesDosParametros);
		
		result.include("detalhesDosParametros", detalhesDosParametros);
		result.include("quartoList", quartosParaReserva);
	}
	
	public void limparReserva(){
		reservasView.clear();
		result.of(this).parametrosIniciais();
	}
	
	public void responsavelReserva(){}
	
	private List<InformativoDeQuartos> buscarQuartosDisponiveis(DetalhesDosParametros detalhesDosParametros) {
		
		Reserva reservaComTodosOsQuartos = new Reserva();
		reservaComTodosOsQuartos.setInicio(new DateTime(reservasView.getChegada().getTime()));
		reservaComTodosOsQuartos.setFim(new DateTime(reservasView.getSaida().getTime()));
		reservaComTodosOsQuartos.setNumeroAdultos(detalhesDosParametros.getNumeroAdultos());
		reservaComTodosOsQuartos.setNumeroCriancas0a5(detalhesDosParametros.getNumeroCriancas0a5());
		reservaComTodosOsQuartos.setNumeroCriancas6a16(detalhesDosParametros.getNumeroCriancas6a16());
		
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		List<PoliticaDePrecos> politicas = politicaPrecoRepositorio.buscaTodos();
		
		ServicoDeReserva servicoReserva = new ServicoDeReserva(quartos);
		List<Quarto> quartosDisponiveis = servicoReserva.quartosDisponiveisParaAReserva(reservaComTodosOsQuartos);
		
		List<InformativoDeQuartos> quartosParaReserva = new InformativoService(politicas).criarInformativoDeQuartos(reservaComTodosOsQuartos,quartosDisponiveis);
		List<InformativoDeQuartos> result = new ArrayList<InformativoDeQuartos>();

		for (InformativoDeQuartos infoq : quartosParaReserva){
			Quarto quartoDisponivel = new Quarto();
			quartoDisponivel.setId(infoq.getId());
			if (!reservasView.jaTemOQuarto(quartoDisponivel))
				result.add(infoq);
		}
		
		return result;
	}
}

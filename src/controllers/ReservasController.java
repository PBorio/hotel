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
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.validators.ReservaValidation;
import controllers.views.reservas.DetalhesDosParametros;
import controllers.views.reservas.InformativoDeQuartos;
import controllers.views.reservas.ParametrosReserva;
import controllers.views.reservas.ReservasView;
import domain.Hospede;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.CalculoDeValorDaDiariaService;
import domain.servicos.InformativoService;
import domain.servicos.ServicoDeReserva;
import domain.servicos.StatusDeReservasNoDia;
import domain.servicos.helpers.Periodo;
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
	
	public void parametrosIniciais(){
		reservasView.setParametrosReserva(null);
		reservasView.setHospedeResponsavel(null);
		reservasView.getReservas().clear();
		reservasView.setChegada(null);
		reservasView.setSaida(null);
		reservasView.setValorReserva(0.0);
	}
	
	public void reservar(Quarto quarto){
		
		try{
			quarto = quartoRepositorio.buscaPorId(quarto.getId());
			DetalhesDosParametros detalhe = reservasView.getParametrosReserva().primeiroDetalheSemQuarto();
			detalhe.setQuarto(quarto);
			Reserva reserva = new Reserva();
			reserva.addQuarto(quarto);
			reserva.setNumeroAdultos(detalhe.getNumeroAdultos());
			reserva.setNumeroCriancas0a5(detalhe.getNumeroCriancas0a5());
			reserva.setNumeroCriancas6a16(detalhe.getNumeroCriancas6a16());
			reserva.setNumeroCriancas17a18(detalhe.getNumeroCriancas17a18());
			reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
			reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
			
			CalculoDeValorDaDiariaService servicoPrecos = new CalculoDeValorDaDiariaService(politicaPrecoRepositorio.buscaTodos());
			servicoPrecos.calcularEInformarValorNaReserva(reserva);
			
			reservasView.addReserva(reserva);
			
			if (reservasView.precisaDeMaisQuartos()){
				List<InformativoDeQuartos> quartosParaReserva = buscarQuartosDisponiveis();
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
			ReservaValidation validation = new ReservaValidation(validator, reservasView);
			validator = validation.validarHospede(hospede);
			
			if (validator.hasErrors()){
				result.include("reserva",reservasView);
				result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			    validator.onErrorUsePageOf(this).showReserva();
			}

			Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede);
			reservasView.setHospedeResponsavel(hospedeExistente);
			
			for (DetalhesDosParametros detalhe : reservasView.getParametrosReserva().getDetalhes()){
				
				Reserva reserva = new Reserva();
				reserva.addQuarto(detalhe.getQuarto());
				reserva.setNumeroAdultos(detalhe.getNumeroAdultos());
				reserva.setNumeroCriancas0a5(detalhe.getNumeroCriancas0a5());
				reserva.setNumeroCriancas6a16(detalhe.getNumeroCriancas6a16());
				reserva.setNumeroCriancas17a18(detalhe.getNumeroCriancas17a18());
				reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
				reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
				
				Quarto quarto = detalhe.getQuarto();
//				if (quarto.possuiReservasNoMesmoPeriodo(reserva))
//					throw new HotelException("Já existe reserva para o quarto "+quarto.getNumero()+" para este período.");
				
				CalculoDeValorDaDiariaService servicoPrecos = new CalculoDeValorDaDiariaService(politicaPrecoRepositorio.buscaTodos());
				servicoPrecos.calcularEInformarValorNaReserva(reserva);
				reserva.setHospede(hospedeExistente);
				
				reservasView.setValorReserva(reserva.getValorReserva());
//				reservaRepositorio.salva(reserva);
				reservasView.addReserva(reserva);
			}
			result.of(this).showReserva();
			
		}catch(HotelException e){
			result.include("reserva",reservasView);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).showReserva();
		}
	}

	
	public void consulta(){
		
		DateTime inicioPeriodo = new DateTime();
		DateTime fimPeriodo = inicioPeriodo.plusDays(30);
		
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		List<StatusDeReservasNoDia> statuses = new ArrayList<StatusDeReservasNoDia>();
		
		StatusDeReservasNoDia statusDoPrimeiroDia = new StatusDeReservasNoDia(inicioPeriodo, quartos);
		statuses.add(statusDoPrimeiroDia);
		
		DateTime proximoDia = inicioPeriodo.plusDays(1);
		while (proximoDia.isBefore(fimPeriodo)){
			StatusDeReservasNoDia status = new StatusDeReservasNoDia(proximoDia, quartos);
			statuses.add(status);
			proximoDia = proximoDia.plusDays(1);
		}
		
		StatusDeReservasNoDia statusDoUltimoDia = new StatusDeReservasNoDia(fimPeriodo, quartos);
		statuses.add(statusDoUltimoDia);
		
		result.include("statusDeReservaNoDiaList", statuses);
	}
	
	@Get
	@Path("/reservas/{id}")
	public void show(Long id) {
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		result.include("reserva", reserva);
	}

	public void parametrosDetalhes(ParametrosReserva parametrosReserva){
		
		reservasView.setChegada(parametrosReserva.getChegada());
		reservasView.setSaida(parametrosReserva.getSaida());
		reservasView.setNumeroDeQuartos(parametrosReserva.getNumeroDeQuartos());
		reservasView.setParametrosReserva(parametrosReserva);
		
		ReservaValidation validation = new ReservaValidation(validator, reservasView);
		validator = validation.validarBusca();
		
		if (validator.hasErrors()){
		    validator.onErrorUsePageOf(this).parametrosIniciais();
		}
		
		List<InformativoDeQuartos> quartosParaReserva = buscarQuartosDisponiveis();
		DetalhesDosParametros detalhesDosParametros = reservasView.getParametrosReserva().primeiroDetalheSemQuarto();
		
		result.include("detalhesDosParametros", detalhesDosParametros);
		result.include("quartoList", quartosParaReserva);
	}

	private List<InformativoDeQuartos> buscarQuartosDisponiveis() {
		
		Reserva reservaComTodosOsQuartos = new Reserva();
		reservaComTodosOsQuartos.setInicio(new DateTime(reservasView.getChegada().getTime()));
		reservaComTodosOsQuartos.setFim(new DateTime(reservasView.getSaida().getTime()));
		
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		List<PoliticaDePrecos> politicas = politicaPrecoRepositorio.buscaTodos();
		
		ServicoDeReserva servicoReserva = new ServicoDeReserva(quartos);
		List<Quarto> quartosDisponiveis = servicoReserva.quartosDisponiveisParaAReserva(reservaComTodosOsQuartos);
		
		Periodo periodo = new Periodo(reservaComTodosOsQuartos.getInicio(), reservaComTodosOsQuartos.getFim());
		List<InformativoDeQuartos> quartosParaReserva = new InformativoService(politicas).criarInformativoDeQuartos(periodo,quartosDisponiveis);
		List<InformativoDeQuartos> result = new ArrayList<InformativoDeQuartos>();

		for (InformativoDeQuartos infoq : quartosParaReserva){
			Quarto quartoDisponivel = new Quarto();
			quartoDisponivel.setId(infoq.getId());
			if (!reservasView.jaTemOQuarto(quartoDisponivel))
				result.add(infoq);
		}
		
		return result;
	}
	
	public void limparReserva(){
		reservasView.setNumeroDeQuartos(1);
		reservasView.setParametrosReserva(null);
		reservasView.setHospedeResponsavel(null);
		reservasView.getReservas().clear();
		reservasView.setChegada(null);
		reservasView.setSaida(null);
		reservasView.setValorReserva(0.0);
		result.of(this).parametrosIniciais();
	}
	
	public void responsavelReserva(){}
}

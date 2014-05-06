package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import repositorios.CategoriaRepositorio;
import repositorios.HospedeRepositorio;
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
import controllers.views.reservas.ReservasView;
import domain.Categoria;
import domain.Hospede;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.CalculoDeValorDaDiariaService;
import domain.servicos.InformativoService;
import domain.servicos.ServicoDeReserva;
import domain.servicos.StatusDeReservasNoDia;

@Resource
public class ReservasController {
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private HospedeRepositorio hospedeRepositorio;
	private ReservaRepositorio reservaRepositorio;
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Validator validator;

	public ReservasController(Result result, 
			                  CategoriaRepositorio categoriaRepositorio, 
			                  QuartoRepositorio quartoRepositorio, 
			                  HospedeRepositorio hospedeRepositorio,
			                  ReservaRepositorio reservaRepositorio,
			                  PoliticaPrecoRepositorio politicaPrecoRepositorio,
			                  Validator validator){
		this.result = result;
		this.categoriaRepositorio = categoriaRepositorio;
		this.quartoRepositorio = quartoRepositorio;
		this.hospedeRepositorio = hospedeRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.validator = validator;
	}
	
	public void reserva(){
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
	}
	
	public void salva(ReservasView reservasView){
		
		try{
			ReservaValidation validation = new ReservaValidation(validator, reservasView);
			validator = validation.criarValidacoes();
			
			if (validator.hasErrors()){
				result.include("reserva",reservasView);
				result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			    validator.onErrorUsePageOf(this).reserva();
			}
			
			Categoria categoria = categoriaRepositorio.buscaPorId(reservasView.getIdCategoria());
			List<Quarto> quartos = quartoRepositorio.buscaPorCategoria(categoria);
			
			Reserva reserva = new Reserva();
			reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
			reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
			reserva.setNumeroAdultos(reservasView.getNumeroAdultos());
			reserva.setNumeroCriancas0a5(reservasView.getNumeroCriancas0a5());
			reserva.setNumeroCriancas6a16(reservasView.getNumeroCriancas6a16());
			reserva.setNumeroCriancas17a18(reservasView.getNumeroCriacas17a18());
			
			Hospede hospede = hospedeRepositorio.buscaPorEmail(reservasView.getEmailHospede());
				
			if (hospede == null){
				hospede = new Hospede();
				hospede.setNome(reservasView.getNomeHospede()+" "+reservasView.getSobrenomeHospede());
				hospede.setCidade(reservasView.getCidadeHospede());
				hospede.setEmail(reservasView.getEmailHospede());
				hospede.setTelefone(reservasView.getTelefoneHospede());
				hospede.setCelular(reservasView.getCelularHospede());
				hospedeRepositorio.salva(hospede);
			}else{
				hospedeRepositorio.atualiza(hospede);
			}
			
			reserva.setHospede(hospede);
			
			ServicoDeReserva servicoReserva = new ServicoDeReserva(quartos);
			Quarto disponivel = servicoReserva.quartoDisponivelParaAReserva(reserva);
			
			if (disponivel == null)
				throw new HotelException("Não há quarto disponível para esta reserva");
			
			reserva.addQuarto(disponivel);
			
			CalculoDeValorDaDiariaService servicoPrecos = new CalculoDeValorDaDiariaService(politicaPrecoRepositorio.buscaTodos());
			servicoPrecos.calcularEInformarValorNaReserva(reserva);
			
			reservaRepositorio.salva(reserva);
			result.include("reserva", reserva);
			result.of(this).show(reserva.getId());
			
		}catch(HotelException e){
			result.include("reserva",reservasView);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).reserva();
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

	public void quartosDisponiveis(ReservasView reservasView){
		
		Reserva reserva = new Reserva();
		reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
		reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
		
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		List<PoliticaDePrecos> politicas = politicaPrecoRepositorio.buscaTodos();
		
		ServicoDeReserva servicoReserva = new ServicoDeReserva(quartos);
		List<Quarto> quartosDisponiveis = servicoReserva.quartosDisponiveisParaAReserva(reserva);
		List<InformativoDeQuartos> quartosParaReserva = new InformativoService(politicas).criarInformativoDeQuartos(quartosDisponiveis);
		
		result.include("quartoList", quartosDisponiveis);
		result.of(this).reserva();
	}
}

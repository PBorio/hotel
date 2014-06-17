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

@Resource
public class ReservasController {
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private HospedeRepositorio hospedeRepositorio;
	private ReservaRepositorio reservaRepositorio;
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Validator validator;
	private ReservasView reservasView;

	public ReservasController(Result result, 
			                  CategoriaRepositorio categoriaRepositorio, 
			                  QuartoRepositorio quartoRepositorio, 
			                  HospedeRepositorio hospedeRepositorio,
			                  ReservaRepositorio reservaRepositorio,
			                  PoliticaPrecoRepositorio politicaPrecoRepositorio,
			                  Validator validator,
			                  ReservasView reservasView){
		this.result = result;
		this.categoriaRepositorio = categoriaRepositorio;
		this.quartoRepositorio = quartoRepositorio;
		this.hospedeRepositorio = hospedeRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.validator = validator;
		this.reservasView = reservasView;
	}
	
	public void reserva(){
	}
	
	public void reservar(Quarto quarto){
		
		try{
			quarto = quartoRepositorio.buscaPorId(quarto.getId());
			reservasView.addQuarto(quarto);
			result.of(this).showReserva();
			
		}catch(HotelException e){
			result.include("reserva",reservasView);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).reserva();
		}
	}
	
	public void showReserva() {
		
	}

	public void confirmar(Hospede hospede){
		
		try{
			if (hospede.getEmail() == null)
				throw new HotelException("Email não informado");
			
			Hospede hospedeExistente = hospedeRepositorio.buscaPorEmail(hospede.getEmail());
			
			if (hospedeExistente == null){
				hospedeExistente = new Hospede();
				hospedeExistente.setNome(hospede.getNome());
				hospedeExistente.setSobrenome(hospede.getSobrenome());
				hospedeExistente.setEmail(hospede.getEmail());
				hospedeExistente.setCidade(hospede.getCidade());
				hospedeExistente.setTelefone(hospede.getTelefone());
				hospedeExistente.setCelular(hospede.getCelular());
				hospedeRepositorio.salva(hospedeExistente);
			}else{
				hospedeExistente.setNome(hospede.getNome());
				hospedeExistente.setSobrenome(hospede.getSobrenome());
				hospedeExistente.setCidade(hospede.getCidade());
				hospedeExistente.setTelefone(hospede.getTelefone());
				hospedeExistente.setCelular(hospede.getCelular());
				hospedeRepositorio.atualiza(hospedeExistente);
			}
			
			reservasView.setHospedeResponsavel(hospedeExistente);
			
			ReservaValidation validation = new ReservaValidation(validator, reservasView);
			validator = validation.criarValidacoes();
			
			if (validator.hasErrors()){
				result.include("reserva",reservasView);
				result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
			    validator.onErrorUsePageOf(this).reserva();
			}
			
			Reserva reserva = new Reserva();
			reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
			reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
			reserva.setNumeroAdultos(reservasView.getNumeroAdultos());
			reserva.setNumeroCriancas0a5(reservasView.getNumeroCriancas0a5());
			reserva.setNumeroCriancas6a16(reservasView.getNumeroCriancas6a16());
			reserva.setNumeroCriancas17a18(reservasView.getNumeroCriancas17a18());
			
			reserva.setHospede(hospedeExistente);
			
			//TODO Verificar disponibilidade
			for (Quarto quarto : reservasView.getQuartos()){
				quarto = quartoRepositorio.buscaPorId(quarto.getId());
				reserva.addQuarto(quarto);
			}
			
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

	public void quartosDisponiveis(ParametrosReserva parametrosReserva){
		
		reservasView.setChegada(parametrosReserva.getChegada());
		reservasView.setSaida(parametrosReserva.getSaida());
		reservasView.setNumeroAdultos(parametrosReserva.getNumeroAdultos());
		reservasView.setNumeroCriancas0a5(parametrosReserva.getNumeroCriancas0a5());
		reservasView.setNumeroCriancas6a16(parametrosReserva.getNumeroCriancas6a16());
		reservasView.setNumeroCriancas17a18(parametrosReserva.getNumeroCriancas17a18());
		
		Reserva reservaComTodosOsQuartos = new Reserva();
		reservaComTodosOsQuartos.setInicio(new DateTime(reservasView.getChegada().getTime()));
		reservaComTodosOsQuartos.setFim(new DateTime(reservasView.getSaida().getTime()));
		
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		List<PoliticaDePrecos> politicas = politicaPrecoRepositorio.buscaTodos();
		
		ServicoDeReserva servicoReserva = new ServicoDeReserva(quartos);
		List<Quarto> quartosDisponiveis = servicoReserva.quartosDisponiveisParaAReserva(reservaComTodosOsQuartos);
		
		Periodo periodo = new Periodo(reservaComTodosOsQuartos.getInicio(), reservaComTodosOsQuartos.getFim());
		List<InformativoDeQuartos> quartosParaReserva = new InformativoService(politicas).criarInformativoDeQuartos(periodo,quartosDisponiveis);
		
		result.include("quartoList", quartosParaReserva);
		result.of(this).reserva();
	}
	
	public void responsavelReserva(){}
}

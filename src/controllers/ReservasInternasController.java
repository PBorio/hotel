package controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import org.joda.time.DateTime;

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
import controllers.calendarios.CalendarioBuilder;
import controllers.validators.HospedeValidation;
import controllers.views.reservas.ReservasInternasView;
import domain.AgrupadorReservas;
import domain.Hospede;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.CalculoDeValorDaDiariaService;
import domain.servicos.CalculoDeValorPorPeriodoService;
import domain.servicos.HotelCalendario;
import domain.servicos.interfaces.HospedeService;


@Resource
public class ReservasInternasController {
	
	
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private ReservaRepositorio reservaRepositorio;
	private ReservasInternasView reservasInternasView;
	private Validator validator;
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private HospedeService hospedeService;

	public ReservasInternasController(Result result, QuartoRepositorio quartoRepositorio, HospedeService hospedeService,
									 ReservaRepositorio reservaRepositorio, PoliticaPrecoRepositorio politicaPrecoRepositorio,
									 ReservasInternasView reservasInternasView,	 Validator validator){
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.hospedeService = hospedeService;
		this.reservaRepositorio = reservaRepositorio;
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.reservasInternasView = reservasInternasView;
		this.validator = validator;
	}
	
	@Get
	@Path("/reserva/interna")
	public void reservaInterna(){
		
	}
	
	@Get
	@Path("/nova/reserva/{idQuarto}/{dia}/{mes}/{ano}/{segundaCalendario}/{mesCalendario}/{anoCalendario}")
	public void novaReserva(Long idQuarto, Integer dia, Integer mes, Integer ano, Integer segundaCalendario, Integer mesCalendario, Integer anoCalendario) {
		DateTime inicioPeriodo = new DateTime(anoCalendario, mesCalendario, segundaCalendario, 0, 0);
		HotelCalendario hotelCalendario = new CalendarioBuilder(quartoRepositorio).criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		try{
			Quarto quarto = quartoRepositorio.buscaPorId(idQuarto);
			DateTime dataReserva = new DateTime(ano, mes, dia, 0,0,0,0);
			
			if (quarto.possuiReservaNoDia(inicioPeriodo))
				throw new HotelException("Quarto "+quarto.getNumero()+" já possui reserva na data.");
				
			Reserva reserva = new Reserva();
			reserva.setQuarto(quarto);
			reserva.setInicio(dataReserva);
			reserva.setNumeroAdultos(2);
			reservasInternasView.addReserva(reserva);
		}catch(HotelException e){
			validator.add(new ValidationMessage(e.getMessage(),"erro.na.reserva",e.getMessage()));
			validator.onErrorUsePageOf(ConsultasController.class).consulta();
		}
		result.of(ConsultasController.class).consulta();
	}
	
	@Get
	@Path("/limpar/nova/reserva/{segundaCalendario}/{mesCalendario}/{anoCalendario}")
	public void novaReserva(Integer segundaCalendario, Integer mesCalendario, Integer anoCalendario) {
		DateTime inicioPeriodo = new DateTime(anoCalendario, mesCalendario, segundaCalendario, 0, 0);
		HotelCalendario hotelCalendario = new CalendarioBuilder(quartoRepositorio).criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		reservasInternasView.clear();
		result.of(ConsultasController.class).consulta();
	}
	
	@Get("/reservas/recalcula/valor.json")
	 public void vendas(DateTime inicio, DateTime fim, Long idQuarto, Integer numeroAdultos, Integer numero0a5, Integer numero6a16, Integer numero17a18){
		
		Quarto quarto = quartoRepositorio.buscaPorId(idQuarto);
		List<PoliticaDePrecos> politicas = politicaPrecoRepositorio.buscaTodos();
		
		Reserva reserva = new Reserva();
		reserva.setInicio(inicio);
		reserva.setFim(fim);
		reserva.setQuarto(quarto);
		reserva.setNumeroAdultos(numeroAdultos);
		reserva.setNumeroCriancas0a5(numero0a5);
		reserva.setNumeroCriancas6a16(numero6a16);
		reserva.setNumeroCriancas17a18(numero17a18);
		
		CalculoDeValorDaDiariaService calculoDiaria = new CalculoDeValorDaDiariaService(politicas);
		calculoDiaria.calcularEInformarValorNaReserva(reserva);
		
		CalculoDeValorPorPeriodoService calculoPorPeriodo = new CalculoDeValorPorPeriodoService();
		Double valorReserva = calculoPorPeriodo.calcularValor(reserva);
		reserva.setValorReserva(valorReserva);
		
		 result.use(json()).withoutRoot()
			.from(reserva)
			.serialize();
	 }
	
	@Post
	@Path("/confirmar/reserva/interna")
	public void confirmarReservaInterna(ReservasInternasView reservasInternasView, Hospede hospede){
		try{
			HospedeValidation validation = new HospedeValidation(validator);
			validator = validation.validarHospede(hospede);
			
			if (validator.hasErrors()){
				result.include("reservasInternasView",reservasInternasView);
			    validator.onErrorUsePageOf(this).reservaInterna();
			}

			Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede);
			
			AgrupadorReservas agrupadorReservas = new AgrupadorReservas();
			for (Reserva reserva : reservasInternasView.getReservas()){
				Quarto quarto = reserva.getQuarto();
				if (quarto.possuiReservasNoMesmoPeriodo(reserva))
					throw new HotelException("Já existe reserva para o quarto "+quarto.getNumero()+" para este período.");
				reserva.setHospede(hospedeExistente);
				agrupadorReservas.addReserva(reserva);
			}
			
			reservaRepositorio.salvarReservas(agrupadorReservas);
			this.reservasInternasView.clear();
			result.redirectTo(ConsultasController.class).consulta();
			
		}catch(HotelException e){
			result.include("reservasInternasView",reservasInternasView);
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).reservaInterna();
		}
	}

}

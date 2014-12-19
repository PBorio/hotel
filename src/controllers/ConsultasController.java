package controllers;

import java.util.List;

import org.joda.time.DateTime;

import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.views.reservas.ReservasInternasView;
import domain.AgrupadorReservas;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.HotelCalendario;

@Resource
public class ConsultasController {
	
	private final Result result;
	private final QuartoRepositorio quartoRepositorio;
	private final ReservaRepositorio reservaRepositorio;
	private final ReservasInternasView reservasInternasView;
	private Validator validator;

	public ConsultasController(Result result, 
								QuartoRepositorio quartoRepositorio, 
								ReservaRepositorio reservaRepositorio, 
								ReservasInternasView reservasInternasView,
								Validator validator){
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.reservasInternasView = reservasInternasView;
		this.validator = validator;
	}
	
	@Get
	@Path("/consultas")
	public void consulta(){
		DateTime inicioPeriodo = new DateTime();
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
	}
	
	@Get
	@Path("/pagamento/{id}")
	public void pagamento(Long id) {
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		result.include("reserva", reserva);
	}
	
	@Get
	@Path("/pagamento/lote/{idAgrupador}")
	public void pagamentoLote(Long idAgrupador){
		AgrupadorReservas agrupadorReservas = reservaRepositorio.buscarAgrupadorPorId(idAgrupador);
		result.include("agrupadorReservas", agrupadorReservas);
	}
	
	@Get
	@Path("/nova/reserva/{idQuarto}/{dia}/{mes}/{ano}/{segundaCalendario}/{mesCalendario}/{anoCalendario}")
	public void novaReserva(Long idQuarto, Integer dia, Integer mes, Integer ano, Integer segundaCalendario, Integer mesCalendario, Integer anoCalendario) {
		DateTime inicioPeriodo = new DateTime(anoCalendario, mesCalendario, segundaCalendario, 0, 0);
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		try{
			Quarto quarto = quartoRepositorio.buscaPorId(idQuarto);
			DateTime dataReserva = new DateTime(ano, mes, dia, 0,0,0,0);
			Reserva reserva = new Reserva();
			reserva.setQuarto(quarto);
			reserva.setInicio(dataReserva);
			reservasInternasView.addReserva(reserva);
		}catch(HotelException e){
			validator.add(new ValidationMessage(e.getMessage(),"erro.na.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).consulta();
		}
		result.of(this).consulta();
	}
	
	@Get
	@Path("/limpar/nova/reserva/{segundaCalendario}/{mesCalendario}/{anoCalendario}")
	public void novaReserva(Integer segundaCalendario, Integer mesCalendario, Integer anoCalendario) {
		DateTime inicioPeriodo = new DateTime(anoCalendario, mesCalendario, segundaCalendario, 0, 0);
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		reservasInternasView.clear();
		result.of(this).consulta();
	}
	
	@Get
	@Path("/consultas/proximo/{primeiraSegundaFeira}/{mes}/{ano}")
	public void proxima(Integer primeiraSegundaFeira, Integer mes, Integer ano){
		DateTime inicioPeriodo = new DateTime(ano, mes, primeiraSegundaFeira, 0, 0);
		inicioPeriodo = inicioPeriodo.plusWeeks(1);
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		result.of(this).consulta();
	}
	
	@Get
	@Path("/consultas/anterior/{primeiraSegundaFeira}/{mes}/{ano}")
	public void anterior(Integer primeiraSegundaFeira, Integer mes, Integer ano){
		DateTime inicioPeriodo = new DateTime(ano, mes, primeiraSegundaFeira, 0, 0);
		inicioPeriodo = inicioPeriodo.minusWeeks(1);
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		result.of(this).consulta();
	}

	private HotelCalendario criarCalendario(DateTime inicioPeriodo) {
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		HotelCalendario hotelCalendario = new HotelCalendario(quartos, inicioPeriodo);
		return hotelCalendario;
	}

}

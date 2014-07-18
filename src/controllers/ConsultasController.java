package controllers;

import java.util.List;

import org.joda.time.DateTime;

import controllers.views.reservas.ReservasView;

import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Quarto;
import domain.Reserva;
import domain.servicos.HotelCalendario;

@Resource
public class ConsultasController {
	
	private final Result result;
	private final QuartoRepositorio quartoRepositorio;
	private final ReservaRepositorio reservaRepositorio;
	private final ReservasView reservasView;

	public ConsultasController(Result result, QuartoRepositorio quartoRepositorio, ReservaRepositorio reservaRepositorio, ReservasView reservasView){
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.reservasView = reservasView;
	}
	
	public void consulta(){
		DateTime inicioPeriodo = new DateTime();
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
	}
	
	@Get
	@Path("/consultas/{id}")
	public void show(Long id) {
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		result.include("reserva", reserva);
	}
	
	@Get
	@Path("/consultas/")
	public void novaReserva() {
		reservasView.clear();
		result.of(ReservasController.class).parametrosIniciais();
	}
	
	@Get
	@Path("/consultas/proximo/{mes}/{ano}")
	public void proxima(Integer mes, Integer ano){
		DateTime inicioPeriodo = new DateTime(ano, mes, 1, 0, 0);
		inicioPeriodo = inicioPeriodo.plusMonths(1);
		HotelCalendario hotelCalendario = criarCalendario(inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);
		result.of(this).consulta();
	}
	
	@Get
	@Path("/consultas/anterior/{mes}/{ano}")
	public void anterior(Integer mes, Integer ano){
		DateTime inicioPeriodo = new DateTime(ano, mes, 1, 0, 0);
		inicioPeriodo = inicioPeriodo.minusMonths(1);
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

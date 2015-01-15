package controllers;

import org.joda.time.DateTime;

import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import controllers.calendarios.CalendarioBuilder;
import domain.AgrupadorReservas;
import domain.Reserva;
import domain.servicos.HotelCalendario;

@Resource
public class ConsultasController {
	
	private final Result result;
	private final QuartoRepositorio quartoRepositorio;
	private final ReservaRepositorio reservaRepositorio;

	public ConsultasController(Result result, 
								QuartoRepositorio quartoRepositorio, 
								ReservaRepositorio reservaRepositorio){
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
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
		return new CalendarioBuilder(quartoRepositorio).criarCalendario(inicioPeriodo);
	}

}

package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;


import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;

import domain.Quarto;
import domain.Reserva;
import domain.servicos.HotelCalendario;
import domain.servicos.StatusDeReservasNoDia;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ConsultasController {
	
	private final Result result;
	private final QuartoRepositorio quartoRepositorio;
	private final ReservaRepositorio reservaRepositorio;

	public ConsultasController(Result result, QuartoRepositorio quartoRepositorio, ReservaRepositorio reservaRepositorio){
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
	}
	
	public void consulta(){
		
		DateTime inicioPeriodo = new DateTime();
		DateTime fimPeriodo = inicioPeriodo.plusDays(30);
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		HotelCalendario hotelCalendario = new HotelCalendario(quartos, inicioPeriodo);
		result.include("hotelCalendario", hotelCalendario);

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
	@Path("/consultas/{id}")
	public void show(Long id) {
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		result.include("reserva", reserva);
	}

}

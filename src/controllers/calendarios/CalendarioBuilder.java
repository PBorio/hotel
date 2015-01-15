package controllers.calendarios;

import java.util.List;

import org.joda.time.DateTime;

import repositorios.QuartoRepositorio;
import domain.Quarto;
import domain.servicos.HotelCalendario;

public class CalendarioBuilder {
	
	private QuartoRepositorio quartoRepositorio;

	public CalendarioBuilder(QuartoRepositorio quartoRepositorio){
		this.quartoRepositorio = quartoRepositorio;
	}
	
	public HotelCalendario criarCalendario(DateTime inicioPeriodo) {
		List<Quarto> quartos = quartoRepositorio.buscaTodos();
		HotelCalendario hotelCalendario = new HotelCalendario(quartos, inicioPeriodo);
		return hotelCalendario;
	}

}

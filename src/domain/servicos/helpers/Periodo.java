package domain.servicos.helpers;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Periodo {
	
	DateTime inicio;
	DateTime fim;
	
	public Periodo(DateTime inicio, DateTime fim){
		this.inicio = inicio;
		this.fim = fim;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public DateTime getFim() {
		return fim;
	}

	public boolean coincideCom(Periodo outroPeriodo) {
		Interval destaReserva = new Interval(inicio, fim);
		Interval daOutraReserva = new Interval(outroPeriodo.getInicio(), outroPeriodo.getFim());
		return destaReserva.overlaps(daOutraReserva);
	}

}

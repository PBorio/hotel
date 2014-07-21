package domain.servicos;

import org.joda.time.DateTime;

import domain.servicos.interfaces.DiaDoCalendario;

public class DiaCabecalho implements DiaDoCalendario {

	private DateTime dia;

	public DiaCabecalho(DateTime dia) {
		this.dia = dia;
	}

	public String getTexto() {
		return dia.dayOfWeek().getAsShortText()+"  "+dia.dayOfMonth().getAsString();
	}

	public boolean isFimDeSemana() {
		return (this.dia.getDayOfWeek() == 6 || this.dia.getDayOfWeek() == 7);
	}


}

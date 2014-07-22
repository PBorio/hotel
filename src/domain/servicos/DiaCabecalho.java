package domain.servicos;

import org.joda.time.DateTime;

public class DiaCabecalho {

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
	
	public boolean isHoje() {
		DateTime hoje = new DateTime().withTimeAtStartOfDay();
		DateTime dia = this.dia.withTimeAtStartOfDay();
		return (hoje.equals(dia));
	}


}

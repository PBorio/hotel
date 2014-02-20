package domain.servicos;

import org.joda.time.DateTime;

import domain.Quarto;
import domain.servicos.tipos.TipoStatusQuarto;

public class StatusQuarto {
	
	private Quarto quarto;
	
	public StatusQuarto(Quarto quarto){
		this.quarto = quarto;
	}
	
	public TipoStatusQuarto statusNaData(DateTime data){
		if (quarto.possuiReservaNoDia(data))
			return TipoStatusQuarto.RESERVADO;
		else
			return TipoStatusQuarto.LIVRE;
	}
	
}

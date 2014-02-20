package domain.servicos;

import org.joda.time.DateTime;

import domain.Quarto;
import domain.Reserva;
import domain.servicos.tipos.TipoStatusQuarto;

public class StatusQuarto {
	
	private Quarto quarto;
	private DateTime dia;
	
	public StatusQuarto(Quarto quarto, DateTime dia){
		this.quarto = quarto;
		this.dia = dia;
	}
	
	public TipoStatusQuarto getTipoStatusNaData(){
		if (quarto.possuiReservaNoDia(dia))
			return TipoStatusQuarto.RESERVADO;
		else
			return TipoStatusQuarto.LIVRE;
	}
	
	public Reserva getReserva(){
		return quarto.reservaNaData(dia); 
	}
}

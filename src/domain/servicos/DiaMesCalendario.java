package domain.servicos;

import org.joda.time.DateTime;

import domain.Quarto;
import domain.Reserva;
import domain.nulos.ReservaNulo;
import domain.servicos.interfaces.DiaDoCalendario;
import domain.servicos.tipos.TipoStatusQuarto;

public class DiaMesCalendario implements DiaDoCalendario {

	private final Quarto quarto;
	private final DateTime dia;
	private Reserva reserva;

	public DiaMesCalendario(Quarto quarto, DateTime dia) {
		this.quarto = quarto;
		this.dia = dia;
		reserva = this.quarto.reservaNaData(this.dia);
	}

	public String getTexto() {
		if (reserva instanceof ReservaNulo) 
			return TipoStatusQuarto.LIVRE.getDescription();
		
		return TipoStatusQuarto.RESERVADO.getDescription();
	}
	
	public Long getIdReserva(){
		return reserva.getId();
	}
	

}

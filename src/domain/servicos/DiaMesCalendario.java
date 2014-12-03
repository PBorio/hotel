package domain.servicos;

import org.joda.time.DateTime;

import domain.Quarto;
import domain.Reserva;
import domain.nulos.ReservaNulo;
import domain.servicos.tipos.TipoStatusQuarto;

public class DiaMesCalendario {

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
		
		if (dia.withTimeAtStartOfDay().equals(reserva.getFim().withTimeAtStartOfDay()))
			return TipoStatusQuarto.LIVRE.getDescription();
		
		if (!reserva.isPossuiPagamento()){
			if (dia.withTimeAtStartOfDay().equals(reserva.getInicio().withTimeAtStartOfDay()))
				return TipoStatusQuarto.RESERVA_NAO_CONFIRMADA_PRIMEIRODIA.getDescription();
			
			return TipoStatusQuarto.RESERVA_NAO_CONFIRMADA.getDescription();
		}
		
		if (dia.withTimeAtStartOfDay().equals(reserva.getInicio().withTimeAtStartOfDay()))
			return TipoStatusQuarto.RESERVADO_PRIMEIRODIA.getDescription();
		
		return TipoStatusQuarto.RESERVADO.getDescription();
	}
	
	public String getMarcacao() {
		if (reserva instanceof ReservaNulo) 
			return "";
		
		if (dia.withTimeAtStartOfDay().equals(reserva.getFim().withTimeAtStartOfDay()))
			return "";
		
		if (!reserva.isPossuiPagamento()){
			if (dia.withTimeAtStartOfDay().equals(reserva.getInicio().withTimeAtStartOfDay()))
				return TipoStatusQuarto.RESERVA_NAO_CONFIRMADA_PRIMEIRODIA.getMarcacao();
			
			return TipoStatusQuarto.RESERVA_NAO_CONFIRMADA.getMarcacao();
		}
		
		if (dia.withTimeAtStartOfDay().equals(reserva.getInicio().withTimeAtStartOfDay()))
			return TipoStatusQuarto.RESERVADO_PRIMEIRODIA.getMarcacao();
		
		return TipoStatusQuarto.RESERVADO.getMarcacao();
	}
	
	public Long getIdReserva(){
		return reserva.getId();
	}

}

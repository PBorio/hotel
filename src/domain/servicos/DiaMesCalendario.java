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

	public Integer getDiaDoMes(){
		return this.dia.dayOfMonth().get();
	}
	
	public Integer getMes(){
		return this.dia.monthOfYear().get();
	}
	
	public Integer getAno(){
		return this.dia.year().get();
	}

	public Quarto getQuarto() {
		return quarto;
	}
}

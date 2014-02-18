package domain;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Reserva {

	private DateTime inicio;
	private DateTime fim;
	private Integer numeroAdultos;
	private Integer numeroCriancas0a5;
	private Integer numeroCriancas6a16;
	private Integer numeroCriacas17a18;
	private Hospede hospede;
	
	private DateTime checkin;
	
	private DateTime cancelamento;

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public void setFim(DateTime fim) {
		this.fim = fim;
	}
	
	private DateTime getInicio() {
		return inicio;
	}
	
	private DateTime getFim() {
		return fim;
	}

	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	public void setNumeroCriancas0a5(Integer numeroCriancas0a5) {
		this.numeroCriancas0a5 = numeroCriancas0a5;
	}

	public void setNumeroCriancas6a16(Integer numeroCriancas6a16) {
		this.numeroCriancas6a16 = numeroCriancas6a16;
	}

	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}

	public Integer getNumeroCriancas0a5() {
		return numeroCriancas0a5;
	}

	public Integer getNumeroCriancas6a16() {
		return numeroCriancas6a16;
	}

	public void setNumeroCriancas17a18(Integer numeroCriacas17a18) {
		this.numeroCriacas17a18 = numeroCriacas17a18;
	}

	public Integer getNumeroCriacas17a18() {
		return numeroCriacas17a18;
	}

	public void setNumeroCriacas17a18(Integer numeroCriacas17a18) {
		this.numeroCriacas17a18 = numeroCriacas17a18;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public DateTime getCheckin() {
		return checkin;
	}

	public void setCheckin(DateTime checkin) {
		this.checkin = checkin;
	}

	public DateTime getCancelamento() {
		return cancelamento;
	}

	public void setCancelamento(DateTime cancelamento) {
		this.cancelamento = cancelamento;
	}
	
	public boolean contemAData(DateTime carnaval) {
		return new Interval(inicio, fim).contains(carnaval);
	}

	public boolean coincideCom(Reserva outraReserva) {
		Interval destaReserva = new Interval(inicio, fim);
		Interval daOutraReserva = new Interval(outraReserva.getInicio(), outraReserva.getFim());
		return destaReserva.overlaps(daOutraReserva);
	}

}

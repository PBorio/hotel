package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import domain.interfaces.CalculavelPorPeriodo;
import domain.nulos.ReservaNulo;
import domain.servicos.CalculoDeValorPorPeriodoService;

public class Estadia implements CalculavelPorPeriodo {

	private Reserva reserva = new ReservaNulo();
	private List<Hospede> hospedes = new ArrayList<Hospede>();
	private Quarto quarto;
	private DateTime dataCheckin;
	private DateTime previsaoCheckout;
	private DateTime dataCheckout;
	private DateTime dataCancelamento;
	private Double valorDiaria;

	public void aPartirDaReserva(Reserva reserva) {
		this.reserva = reserva;
		this.hospedes.add(reserva.getHospede());
		this.quarto = reserva.getQuarto();
		this.dataCheckin = reserva.getInicio();
		this.previsaoCheckout = reserva.getFim();
		this.valorDiaria = reserva.getValorDiaria();
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public DateTime getDataCheckin() {
		return dataCheckin;
	}

	public DateTime getPrevisaoCheckout() {
		return previsaoCheckout;
	}

	public DateTime getDataCheckout() {
		return dataCheckout;
	}

	public List<Hospede> getHospedes() {
		return this.hospedes;
	}

	public void setDataCheckin(DateTime dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public void addHospede(Hospede hospede) {
		this.hospedes.add(hospede);
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public boolean isAberta() {
		return (!isFechada() && !isCancelada());
	}
	
	public boolean isFechada() {
		return (!isCancelada() && this.dataCheckout != null);
	}

	public boolean isCancelada() {
		return (this.dataCancelamento != null);
	}

	public void fechar(DateTime checkout) {
		this.dataCheckout = checkout;
	}

	public void cancelar(DateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Double getValorDiaria() {
		return this.valorDiaria;
	}
	
	public DateTime getInicio() {
		return this.dataCheckin;
	}

	public DateTime getFim() {
		return this.dataCheckout;
	}

	public Double valorAteAData(DateTime data) {
		
		if (isCancelada())
			return 0.0;
		
		if (isFechada())
			data = dataCheckout;
			
		return new CalculoDeValorPorPeriodoService().calcularValorAteAData(this, data);
	}

}

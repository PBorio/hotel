package domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Estadia {

	private Reserva reserva;
	private List<Hospede> hospedes = new ArrayList<Hospede>();
	private Quarto quarto;
	private DateTime dataCheckin;
	private DateTime previsaoCheckout;
	private DateTime dataCheckout;

	public void aPartirDaReserva(Reserva reserva) {
		this.reserva = reserva;
		this.hospedes.add(reserva.getHospede());
		this.quarto = reserva.getQuarto();
		this.dataCheckin = reserva.getInicio();
		this.previsaoCheckout = reserva.getFim();
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

}

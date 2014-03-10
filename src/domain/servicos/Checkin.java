package domain.servicos;

import domain.Estadia;
import domain.Reserva;

public class Checkin {

	private Reserva reserva;

	private Checkin(Reserva reserva) {
		this.reserva = reserva;
	}

	public static Checkin checkinAPartirDeUmaReserva(Reserva reserva) {
		return new Checkin(reserva);
	}

	public Estadia iniciarEstadia() {
		Estadia estadia = new Estadia();
		estadia.aPartirDaReserva(this.reserva);
		return estadia;
	}

}

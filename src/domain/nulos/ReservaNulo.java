package domain.nulos;

import domain.Hospede;
import domain.Reserva;

public class ReservaNulo extends Reserva {
	
	@Override
	public Hospede getHospede() {
		return new HospedeNulo();
	}

}

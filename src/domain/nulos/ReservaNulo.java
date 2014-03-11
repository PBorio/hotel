package domain.nulos;

import domain.Hospede;
import domain.Quarto;
import domain.Reserva;

public class ReservaNulo extends Reserva {
	
	@Override
	public Quarto getQuarto() {
		return new QuartoNulo();
	}
	
	@Override
	public Hospede getHospede() {
		return new HospedeNulo();
	}

}

package domain;

public class QuartoDaReserva {

	private Reserva reserva;
	private Quarto quarto;

	public QuartoDaReserva(){}
	
	public QuartoDaReserva(Reserva reserva, Quarto quarto) {
		this.reserva = reserva;
		this.quarto = quarto;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Quarto getQuarto() {
		return quarto;
	}

}

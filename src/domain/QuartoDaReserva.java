package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="quarto_reserva")
public class QuartoDaReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="reserva_id")
	private Reserva reserva;
	
	@ManyToOne
	@JoinColumn(name="quarto_id")
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

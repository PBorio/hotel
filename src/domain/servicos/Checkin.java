package domain.servicos;

import org.joda.time.DateTime;

import domain.Estadia;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;

public class Checkin {

	private Reserva reserva;
	private Quarto quarto;
	private Hospede hospede;
	private DateTime dataCheckin;
	private Double valorDiaria;

	private Checkin(Reserva reserva, Quarto quarto) {
		this.reserva = reserva;
		this.quarto = quarto;
	}

	private Checkin(Quarto quarto, Hospede hospede, DateTime dataCheckin, Double valorDiaria) {
		this.quarto = quarto;
		this.hospede = hospede;
		this.dataCheckin = dataCheckin;
		this.valorDiaria = valorDiaria;
	}

	public static Checkin checkinAPartirDeUmaReserva(Reserva reserva, Quarto quarto) {
		return new Checkin(reserva, quarto);
	}

	public Estadia iniciarEstadiaAPartirDeUmaReserva() {
		Estadia estadia = new Estadia();
		estadia.aPartirDaReservaEQuarto(this.reserva, quarto);
		return estadia;
	}
	
	public Estadia iniciarEstadiaSemReserva() {
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(dataCheckin);
		estadia.addHospede(hospede);
		estadia.setQuarto(quarto);
		estadia.setValorDiaria(valorDiaria);
		return estadia;
	}

	public static Checkin checkinSemReserva(Quarto quarto, Hospede hospede, DateTime dataCheckin, Double valorDiaria) {
		return new Checkin(quarto, hospede, dataCheckin, valorDiaria);
	}

}

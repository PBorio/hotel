package domain.servicos;

import java.util.ArrayList;
import java.util.List;

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

	private Checkin(Reserva reserva) {
		this.reserva = reserva;
	}

	private Checkin(Quarto quarto, Hospede hospede, DateTime dataCheckin, Double valorDiaria) {
		this.quarto = quarto;
		this.hospede = hospede;
		this.dataCheckin = dataCheckin;
		this.valorDiaria = valorDiaria;
	}

	public static Checkin checkinAPartirDeUmaReserva(Reserva reserva) {
		return new Checkin(reserva);
	}

	public List<Estadia> iniciarEstadiasAPartirDeUmaReserva() {
		
		List<Estadia> estadias = new ArrayList<Estadia>();
		for (Quarto quarto : reserva.getQuartos()){
			Estadia estadia = new Estadia();
			estadia.aPartirDaReservaEQuarto(this.reserva, quarto);
			estadias.add(estadia);
		}
		return estadias;
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

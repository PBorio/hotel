package controllers.views.reservas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Hospede;
import domain.Reserva;
import domain.exceptions.HotelException;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class ReservasInternasView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515958338769045208L;
	
	private Date chegada;
	
	private Date saida;
	
	private Hospede hospedeResponsavel;
	
	private Double valorReserva = 0.0;

	private List<Reserva> reservas = new ArrayList<Reserva>();

	public Date getChegada() {
		return chegada;
	}

	public void setChegada(Date chegada) {
		this.chegada = chegada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Hospede getHospedeResponsavel() {
		return hospedeResponsavel;
	}

	public void setHospedeResponsavel(Hospede hospedeResponsavel) {
		this.hospedeResponsavel = hospedeResponsavel;
	}

	public Double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(Double valorReserva) {
		this.valorReserva = valorReserva;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void addReserva(Reserva reserva) {
		for (Reserva reservaJaIncluida : this.reservas){
			if (!reservaJaIncluida.getInicio().equals(reserva.getInicio()))
				throw new HotelException("Reservas com datas diferentes não podem ser agrupadas.");
			
			if (reservaJaIncluida.getInicio().equals(reserva.getInicio()) && reservaJaIncluida.getQuarto().equals(reserva.getQuarto()))
				return;
		}
		reservas.add(reserva);
	}

	public void clear() {
		this.chegada = null;
		this.saida = null;
		this.reservas.clear();
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

}

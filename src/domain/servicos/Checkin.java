package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import domain.Estadia;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HospedeInvalidoException;
import domain.exceptions.QuartoInvalidoException;
import domain.exceptions.ReservaInvalidaException;

@Component
@SessionScoped
public class Checkin {

	private Reserva reserva;
	private Quarto quarto;
	private DateTime dataCheckin;
	private Double valorDiaria;
	private List<Hospede> hospedes = new ArrayList<Hospede>();
	

	public Checkin() {}

	public Estadia iniciarEstadiaAPartirDeUmaReserva() {
		validarReserva(this.reserva);
		validarHospedes();
		Estadia estadia = new Estadia();
		estadia.aPartirDaReserva(this.reserva);
		for (Hospede hospede : this.hospedes){
			estadia.addHospede(hospede);
		}
		return estadia;
	}
	
	public Estadia iniciarEstadiaSemReserva() {
		validarHospedes();
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(dataCheckin);
		for (Hospede hospede : this.hospedes){
			estadia.addHospede(hospede);
		}
		estadia.setQuarto(quarto);
		estadia.setValorDiaria(valorDiaria);
		return estadia;
	}

	public void aPartirDaReserva(Reserva reserva) {
		validarReserva(reserva);

		this.reserva = reserva;
		this.quarto = reserva.getQuarto();
	}
	
	public void addHospede(Hospede hospede) {
		this.hospedes.add(hospede);
	}

	private void validarReserva(Reserva reserva) {
		if (reserva == null)
			throw new ReservaInvalidaException("Nenhuma reserva selecionada para o checkin.");
		
		validarQuarto(reserva.getQuarto());
	}

	private void validarQuarto(Quarto quarto) {
		if (quarto == null)
			throw new QuartoInvalidoException("Nenhuma reserva selecionada para o checkin.");
	}
	
	private void validarHospedes() {
		if (this.hospedes.isEmpty())
			throw new HospedeInvalidoException("Nenhum hóspede selecionado para o checkin.");
	}

	public void clear() {
		this.hospedes = new ArrayList<Hospede>();
		this.reserva = null;
		this.quarto = null;
		this.valorDiaria = null;
		this.dataCheckin = null;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public void setDataCheckin(DateTime dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public Reserva getReserva() {
		return this.reserva;
	}
	
	public Quarto getQuarto(){
		return this.quarto;
	}
	
	public boolean isMostraResponsavel(){
		for(Hospede h : this.hospedes){
			if (h.equals(reserva.getHospede()))
				return false;
		}
		return true;
	}
	
	public List<Hospede> getHospedes(){
		return this.hospedes;
	}

	public void removeHospede(Hospede hospede) {
		this.hospedes.remove(hospede);
	}
	
	public Double getValorDiaria(){
		if (this.reserva == null)
			return this.valorDiaria;
		return this.reserva.getValorDiaria();
	}

}

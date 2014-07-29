package domain.servicos;

import java.util.ArrayList;
import java.util.Date;
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
import domain.interfaces.CalculavelPorPeriodo;

@Component
@SessionScoped
public class Checkin implements CalculavelPorPeriodo {

	private Reserva reserva;
	private Quarto quarto;
	private Date dataCheckin;
	private Date dataCheckout;
	private Double desconto;
	private Double valorDiaria;
	private List<Hospede> hospedes = new ArrayList<Hospede>();
	

	public Checkin() {}

	public Estadia iniciarEstadiaAPartirDeUmaReserva() {
		validarReserva(this.reserva);
		validarHospedes();
		Estadia estadia = new Estadia();
		estadia.setReserva(this.reserva);
		estadia.setQuarto(this.reserva.getQuarto());
		estadia.setDataCheckin(new DateTime(this.getDataCheckin()));
		estadia.setPrevisaoCheckout(new DateTime(this.getDataCheckout()));
		estadia.setValorDiaria(this.getValorDiaria());
		for (Hospede hospede : this.hospedes){
			estadia.addHospede(hospede);
		}
		return estadia;
	}
	
	public Estadia iniciarEstadiaSemReserva() {
		validarHospedes();
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new DateTime(dataCheckin));
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

	public void clear() {
		this.hospedes = new ArrayList<Hospede>();
		this.reserva = null;
		this.quarto = null;
		this.valorDiaria = null;
		this.dataCheckin = null;
		this.dataCheckout = null;
		this.desconto = null;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public void setDataCheckin(Date dataCheckin) {
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
	
	public Date getDataCheckout() {
		if (this.dataCheckout == null)
			return this.reserva.getFim().toDate();
		return dataCheckout;
	}

	public void setDataCheckout(Date dataCheckout) {
		this.dataCheckout = dataCheckout;
	}

	public Date getDataCheckin() {
		if (this.dataCheckin == null)
			return this.reserva.getInicio().toDate();
		return dataCheckin;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	public Double getValorFinal(){
		return new CalculoDeValorPorPeriodoService().calcularValorAteAData(this, new DateTime(this.getDataCheckout()));
	}
	
	public Double getSaldoAPagar(){
		return (this.getValorFinal() - getValorPago());
	}
	
	public Double getValorPago() {
		if (reserva == null)
			return 0.0;
		return reserva.getValorPago();
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

	public DateTime getInicio() {
		return new DateTime(this.getDataCheckin());
	}

	public DateTime getFim() {
		return new DateTime(this.getDataCheckout());
	}
	
	public Double getValorDiaria(){
		if (this.valorDiaria == null)
			return this.reserva.getValorDiaria();
		
		if (this.desconto == null) this.desconto = 0.0;
		return this.valorDiaria * (1 - (desconto/100)) ;
	}
}

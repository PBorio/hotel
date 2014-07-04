package controllers.views.reservas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Hospede;
import domain.Quarto;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class ReservasView {
	
	private Date chegada;
	
	private Date saida;
	
	private List<ParametrosReserva> parametrosReserva = new ArrayList<ParametrosReserva>();
	
	private Integer numeroDeQuartos;
	
	private List<Quarto> quartos = new ArrayList<Quarto>();

	private Hospede hospedeResponsavel;
	
	private Double valorReserva = 0.0;

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

	public List<Quarto> getQuartos() {
		return quartos;
	}

	public void addQuarto(Quarto quarto) {
		if (!quartos.contains(quarto))
			quartos.add(quarto);
	}
	
	public Double getValorReserva(){
		return this.valorReserva;
	}
	
	public void setValorReserva(Double valorReserva){
		this.valorReserva = valorReserva;
	}

	public void setHospedeResponsavel(Hospede hospede) {
		this.hospedeResponsavel = hospede;
	}

	public Hospede getHospedeResponsavel() {
		return this.hospedeResponsavel;
	}

	public String getEmailHospede() {
		if (this.hospedeResponsavel == null)
			return null;
		
		return this.hospedeResponsavel.getEmail();
	}

	public String getTelefoneHospede() {
		if (this.hospedeResponsavel == null)
			return null;
		
		return this.hospedeResponsavel.getTelefone();
	}

	public Integer getNumeroDeQuartos() {
		return numeroDeQuartos;
	}

	public void setNumeroDeQuartos(Integer numeroDeQuartos) {
		this.numeroDeQuartos = numeroDeQuartos;
	}

	public boolean precisaDeMaisQuartos() {
		int numeroQuarto = 0;
		if (this.numeroDeQuartos != null)
			numeroQuarto = this.numeroDeQuartos;
		return (numeroQuarto > quartos.size());
	}

	public List<ParametrosReserva> getParametrosReserva() {
		return parametrosReserva;
	}

}

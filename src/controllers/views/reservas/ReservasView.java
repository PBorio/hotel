package controllers.views.reservas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;

@Component
@SessionScoped
public class ReservasView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2117445091151435359L;

	private Date chegada;
	
	private Date saida;
	
	private ParametrosReserva parametrosReserva;
	
	private Integer numeroDeQuartos;
	
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

	public List<Quarto> getQuartos() {
		if (this.parametrosReserva == null)
			return null;
		
		return this.parametrosReserva.getQuartos();
	}

	public void addQuarto(Quarto quarto) {
		parametrosReserva.primeiroDetalheSemQuarto().setQuarto(quarto);
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
		return (numeroQuarto > getQuartos().size());
	}

	public ParametrosReserva getParametrosReserva() {
		return parametrosReserva;
	}

	public void setParametrosReserva(ParametrosReserva parametrosReserva) {
		this.parametrosReserva = parametrosReserva;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public void setReservas(List<Reserva> reservas){
		this.reservas = reservas;
	}
	
	public Integer getNumeroDeQuartosJaSelecionados(){
		if (parametrosReserva == null)
			return 0;
		
		return parametrosReserva.getNumeroDeQuartosJaSelecionados();
	}

	public boolean jaTemOQuarto(Quarto quarto) {

		if (parametrosReserva == null || parametrosReserva.getDetalhes() == null)
			return false;

		return parametrosReserva.jaTemOQuarto(quarto);
	}

	public void clear() {
		this.setNumeroDeQuartos(1);
		this.setParametrosReserva(null);
		this.setHospedeResponsavel(null);
		this.getReservas().clear();
		this.setChegada(null);
		this.setSaida(null);
		this.setValorReserva(0.0);
	}

	public boolean ehParaUmQuartoSo() {
		return this.parametrosReserva.ehParaUmQuartoSo();
	}

}

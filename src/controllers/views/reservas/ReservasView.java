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
	
	private Long idCategoria;
	
	private Date chegada;
	
	private Date saida;
	
	private Integer numeroCriancas0a5;
	
	private Integer numeroCriancas6a16;
	
	private Integer numeroCriacas17a18;
	
	private Integer numeroAdultos;
	
	private Integer numeroDeQuartos;
	
	private List<Quarto> quartos = new ArrayList<Quarto>();

	private Hospede hospedeResponsavel;
	
	private Double valorReserva = 0.0;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

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

	public Integer getNumeroCriancas0a5() {
		return numeroCriancas0a5;
	}

	public void setNumeroCriancas0a5(Integer numeroCriancas0a5) {
		this.numeroCriancas0a5 = numeroCriancas0a5;
	}

	public Integer getNumeroCriancas6a16() {
		return numeroCriancas6a16;
	}

	public void setNumeroCriancas6a16(Integer numeroCriancas6a16) {
		this.numeroCriancas6a16 = numeroCriancas6a16;
	}

	public Integer getNumeroCriancas17a18() {
		return numeroCriacas17a18;
	}

	public void setNumeroCriancas17a18(Integer numeroCriacas17a18) {
		this.numeroCriacas17a18 = numeroCriacas17a18;
	}

	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}

	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
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

}

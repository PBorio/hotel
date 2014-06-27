package controllers.views.reservas;

import java.util.Date;

public class ParametrosReserva {
	
	private Integer numeroCriancas0a5;
	
	private Integer numeroCriancas6a16;
	
	private Integer numeroCriancas17a18;
	
	private Integer numeroAdultos; 
	
	private Date chegada;
	
	private Date saida;
	
	private Integer numeroDeQuartos;

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

	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}

	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
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

	public Integer getNumeroCriancas17a18() {
		return numeroCriancas17a18;
	}

	public void setNumeroCriancas17a18(Integer numeroCriancas17a18) {
		this.numeroCriancas17a18 = numeroCriancas17a18;
	}

	public Integer getNumeroDeQuartos() {
		return numeroDeQuartos;
	}

	public void setNumeroDeQuartos(Integer numeroDeQuartos) {
		this.numeroDeQuartos = numeroDeQuartos;
	}

}

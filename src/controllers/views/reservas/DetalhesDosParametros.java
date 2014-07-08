package controllers.views.reservas;

import domain.Quarto;

public class DetalhesDosParametros {
	
	private Integer numeroCriancas0a5;
	private Integer numeroCriancas6a16;
	private Integer numeroCriancas17a18;
	private Integer numeroAdultos;
	private Quarto quarto;
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
		return numeroCriancas17a18;
	}
	public void setNumeroCriancas17a18(Integer numeroCriancas17a18) {
		this.numeroCriancas17a18 = numeroCriancas17a18;
	}
	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}
	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}
	public Quarto getQuarto() {
		return this.quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

}

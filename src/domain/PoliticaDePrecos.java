package domain;

import org.joda.time.DateTime;

public class PoliticaDePrecos {

	private Long id;
	private String descricao;
	private Categoria categoria;
	private DateTime fim;
	private DateTime inicio;
	private Double valorDiaria;
	private boolean padrao = false;

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public void setFim(DateTime fim) {
		this.fim = fim;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public DateTime getFim() {
		return fim;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public void setValorDiaria(Double valor) {
		this.valorDiaria = valor;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setPadrao(boolean padrao) {
		this.padrao  = padrao;
	}

	public boolean isPadrao() {
		return padrao;
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}


}

package controllers.views.reservas;

import java.util.Date;

import org.joda.time.DateTime;

public class InformativoDeQuartos {

	private Long id;
	private String numero;
	private String descricao;
	private Double valorDaDiaria;
	private Date inicio;
	private Date fim;
	private String observacao;
	private String categoria;

	public InformativoDeQuartos(Long id, String numero, String descricao, String observacao, Double valorDaDiaria, String categoria, DateTime inicio, DateTime fim) {
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.observacao = observacao;
		this.valorDaDiaria = valorDaDiaria;
		this.categoria = categoria;
		this.inicio = inicio.toDate();
		this.fim = fim.toDate();
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValorDaDiaria() {
		return valorDaDiaria;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}

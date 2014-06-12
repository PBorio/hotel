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

	public InformativoDeQuartos(Long id, String numero, String descricao, Double valorDaDiaria, DateTime inicio, DateTime fim) {
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.valorDaDiaria = valorDaDiaria;
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

}

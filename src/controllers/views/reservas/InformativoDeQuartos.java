package controllers.views.reservas;

public class InformativoDeQuartos {

	private Long id;
	private String numero;
	private String descricao;
	private Double valorDaDiaria;

	public InformativoDeQuartos(Long id, String numero, String descricao, Double valorDaDiaria) {
		this.id = id;
		this.numero = numero;
		this.descricao = descricao;
		this.valorDaDiaria = valorDaDiaria;
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

}

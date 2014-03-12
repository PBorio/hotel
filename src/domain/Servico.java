package domain;

public class Servico {

	private String descricao;
	private Double valorSugerido;
	private String observacao;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValorSugerido(Double valorSugerido) {
		this.valorSugerido = valorSugerido;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValorSugerido() {
		return valorSugerido;
	}

	public String getObservacao() {
		return observacao;
	}

}

package domain;

public class Produto {

	private String descricao;
	private Double preco;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPreco() {
		return this.preco;
	}

}

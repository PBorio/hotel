package domain.helpers;

import domain.Consumo;
import domain.Produto;

public class FakeConsumo {

	private Produto produto;
	private Consumo consumo;
	public FakeConsumo(String descricao) {
		consumo = new Consumo();
		produto = new Produto();
		produto.setDescricao(descricao);
		consumo.setProduto(produto);
	}
	public FakeConsumo comValorDe(Double valor) {
		produto.setPreco(valor);
		return this;
	}
	public Consumo build() {
		return consumo;
	}

}

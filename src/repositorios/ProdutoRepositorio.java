package repositorios;

import java.util.List;

import domain.Produto;

public interface ProdutoRepositorio {
	
	public List<Produto> buscaProdutosPorDescricao(String descricao);

}

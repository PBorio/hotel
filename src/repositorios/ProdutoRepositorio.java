package repositorios;

import java.util.List;

import domain.Produto;

public interface ProdutoRepositorio {
	
	public List<Produto> buscaProdutosPorDescricao(String descricao);
	public Produto buscaPorId(Long id);
	public Produto buscaPorEstaDescricao(String descricao);
	public List<Produto> buscaTodos();
	public void salva(Produto produto);
	public void atualiza(Produto produto);

}

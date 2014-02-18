package repositorios;

import java.util.List;

import domain.Categoria;

public interface CategoriaRepositorio {
	
	List<Categoria> buscaTodos();
	
	public Categoria buscaPorId(Long id);
	
	void salva(Categoria quarto);
	
	void atualiza(Categoria quarto);

}

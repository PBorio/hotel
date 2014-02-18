package repositorios;

import java.util.List;

import domain.Categoria;
import domain.Quarto;

public interface QuartoRepositorio {

	List<Quarto> buscaTodos();
	
	public Quarto buscaPorId(Long id);
	
	void salva(Quarto quarto);
	
	void atualiza(Quarto quarto);

	List<Quarto> buscaPorCategoria(Categoria categoria);

}

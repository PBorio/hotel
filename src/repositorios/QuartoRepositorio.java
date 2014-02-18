package repositorios;

import java.util.List;

import domain.Quarto;

public interface QuartoRepositorio {

	List<Quarto> buscaTodos();
	
	public Quarto buscaPorId(Long id);
	
	void salva(Quarto quarto);
	
	void atualiza(Quarto quarto);

}

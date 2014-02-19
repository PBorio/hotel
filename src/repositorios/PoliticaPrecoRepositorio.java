package repositorios;

import java.util.List;

import domain.PoliticaDePrecos;

public interface PoliticaPrecoRepositorio {
	
	List<PoliticaDePrecos> buscaTodos();
	
	public PoliticaDePrecos buscaPorId(Long id);
	
	void salva(PoliticaDePrecos politica);
	
	void atualiza(PoliticaDePrecos politica);


}

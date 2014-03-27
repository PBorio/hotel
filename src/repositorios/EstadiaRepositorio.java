package repositorios;

import java.util.List;

import domain.Estadia;

public interface EstadiaRepositorio {
	
	List<Estadia> buscaTodos();
	
	public Estadia buscaPorId(Long id);
	
	void salva(Estadia hospede);
	
	void atualiza(Estadia hospede);

	List<Estadia> estadiasAbertas();

}

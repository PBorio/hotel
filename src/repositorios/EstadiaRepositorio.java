package repositorios;

import java.util.List;

import domain.Estadia;
import domain.HospedeDaEstadia;

public interface EstadiaRepositorio {
	
	List<Estadia> buscaTodos();
	
	public Estadia buscaPorId(Long id);
	
	void salva(Estadia hospede);
	
	void atualiza(Estadia hospede);

	List<Estadia> estadiasAbertas();

	HospedeDaEstadia buscaHospedeDaEstadiaPorId(Long id);

	void excluirHospede(HospedeDaEstadia hospedeDaEstadia);

}

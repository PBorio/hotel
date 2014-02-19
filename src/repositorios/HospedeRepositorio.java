package repositorios;

import java.util.List;

import domain.Hospede;

public interface HospedeRepositorio {
	
	List<Hospede> buscaTodos();
	
	public Hospede buscaPorId(Long id);
	
	void salva(Hospede hospede);
	
	void atualiza(Hospede hospede);

	Hospede buscaPorEmail(String email);

}

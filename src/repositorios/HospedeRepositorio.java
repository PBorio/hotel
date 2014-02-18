package repositorios;

import java.util.List;

import domain.Hospede;

public interface HospedeRepositorio {
	
	List<Hospede> buscaTodos();
	
	public Hospede buscaPorId(Long id);
	
	void salva(Hospede quarto);
	
	void atualiza(Hospede quarto);

	Hospede buscaPorEmail(String email);

}

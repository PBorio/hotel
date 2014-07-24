package domain.servicos.interfaces;

import repositorios.HospedeRepositorio;
import domain.Hospede;

public interface HospedeService {
	
	public Hospede buscarESalvarOuAtualizar(Hospede hospede);

	public HospedeRepositorio getHospedeRepositorio();

}

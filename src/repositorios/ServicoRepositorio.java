package repositorios;

import java.util.List;

import domain.Servico;

public interface ServicoRepositorio {
	
	public List<Servico> buscaServicosPorDescricao(String descricao);
	public Servico buscaPorId(Long id);
	public Servico buscaPorEstaDescricao(String descricao);
	public List<Servico> buscaTodos();
	public void salva(Servico servico);
	public void atualiza(Servico servico);

}

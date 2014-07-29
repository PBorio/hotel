package repositorios;

import domain.Pagamento;

public interface PagamentoRepositorio {
	
	void salva(Pagamento pagamento);
	
	public Pagamento buscaPorId(Long id);

	public void excluir(Pagamento pagamentoReserva);

	void atualiza(Pagamento pagamentoReserva);

}

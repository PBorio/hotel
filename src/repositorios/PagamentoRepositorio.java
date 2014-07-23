package repositorios;

import domain.PagamentoReserva;

public interface PagamentoRepositorio {
	
	void salva(PagamentoReserva pagamento);
	
	public PagamentoReserva buscaPorId(Long id);

	public void excluir(PagamentoReserva pagamentoReserva);

}

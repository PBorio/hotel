package repositorios.daos;

import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.PagamentoRepositorio;
import domain.Pagamento;
import domain.PagamentoReserva;

@Component
public class PagamentoDAO extends DAO<Pagamento> implements
		PagamentoRepositorio {

	public PagamentoDAO() {
		super(Pagamento.class);
	}

	@Transactional
	public void excluir(Pagamento pagamento) {
		for (PagamentoReserva pr : pagamento.getPagamentoReservas()){
			getEntityManager().remove(pr);
		}
		super.entityManager.remove(pagamento);
	}
	
	@Transactional
	@Override
	public void salva(Pagamento pagamento) {
		super.salva(pagamento);
		super.getEntityManager().flush();
		for (PagamentoReserva pr : pagamento.getPagamentoReservas()){
			getEntityManager().persist(pr);
		}
	}
}

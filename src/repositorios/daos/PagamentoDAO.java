package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.PagamentoRepositorio;
import domain.PagamentoReserva;

@Component
public class PagamentoDAO extends DAO<PagamentoReserva> implements
		PagamentoRepositorio {

	public PagamentoDAO() {
		super(PagamentoReserva.class);
	}
}

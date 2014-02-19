package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.ReservaRepositorio;
import domain.Reserva;

@Component
public class ReservaDAO extends DAO<Reserva> implements ReservaRepositorio {

	public ReservaDAO() {
		super(Reserva.class);
	}
}

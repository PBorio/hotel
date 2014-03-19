package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.EstadiaRepositorio;
import domain.Estadia;


@Component
public class EstadiaDAO extends DAO<Estadia> implements EstadiaRepositorio {

	public EstadiaDAO() {
		super(Estadia.class);
	}
}

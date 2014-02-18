package repositorios.daos;

import repositorios.QuartoRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Quarto;

@Component
public class QuartoDAO extends DAO<Quarto> implements QuartoRepositorio {

	
	public QuartoDAO() {
		super(Quarto.class);
	}
	
}

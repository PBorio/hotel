package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.PoliticaPrecoRepositorio;
import domain.PoliticaDePrecos;

@Component
public class PoliticaPrecoDAO extends DAO<PoliticaDePrecos> implements PoliticaPrecoRepositorio {

	public PoliticaPrecoDAO() {
		super(PoliticaDePrecos.class);
	}

}

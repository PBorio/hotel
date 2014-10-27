package repositorios.daos;

import org.springframework.transaction.annotation.Transactional;

import repositorios.PoliticaPrecoRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.PoliticaDePrecos;

@Component
public class PoliticaPrecoDAO extends DAO<PoliticaDePrecos> implements PoliticaPrecoRepositorio {

	public PoliticaPrecoDAO() {
		super(PoliticaDePrecos.class);
	}

	@Transactional
	public void excluir(PoliticaDePrecos politicaDePrecos) {
		super.entityManager.remove(politicaDePrecos);
	}
}

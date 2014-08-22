package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.SolicitacaoAcessoRepositorio;
import domain.SolicitacaoAcesso;

@Component
public class SolicitacaoAcessoDAO extends DAO<SolicitacaoAcesso> implements SolicitacaoAcessoRepositorio {

	public SolicitacaoAcessoDAO() {
		super(SolicitacaoAcesso.class);
	}

}

package repositorios.daos;

import java.net.URI;

import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.ArquivosRepositorio;
import domain.Arquivo;

@Component
public class ArquivoDAO extends DAO<Arquivo> implements ArquivosRepositorio {

	public ArquivoDAO() {
		super(Arquivo.class);
	}

	@Transactional
	public URI grava(Arquivo arquivo) {
		super.salva(arquivo);
		return URI.create("bd://"+arquivo.getId());
	}

	public Arquivo recupera(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

}

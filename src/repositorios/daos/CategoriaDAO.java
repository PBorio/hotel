package repositorios.daos;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.CategoriaRepositorio;
import domain.Categoria;

@Component
public class CategoriaDAO extends DAO<Categoria> implements	CategoriaRepositorio {

	public CategoriaDAO() {
		super(Categoria.class);
	}

}

package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.ProdutoRepositorio;
import domain.Produto;

@Component
public class ProdutoDAO extends DAO<Produto> implements ProdutoRepositorio {

	public ProdutoDAO() {
		super(Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscaProdutosPorDescricao(String descricao) {

		StringBuilder hql = new StringBuilder();
		hql.append(" From Produto p ");
		hql.append(" where p.descricao like ? ");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(1, "%" + descricao + "%");

		return (List<Produto>) query.getResultList();
	}

}

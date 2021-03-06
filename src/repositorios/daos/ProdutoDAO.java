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
		
		List<Produto> result = query.getResultList();
		return result; 
	}

	@SuppressWarnings("unchecked")
	public Produto buscaPorEstaDescricao(String descricao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From Produto p ");
		hql.append(" where p.descricao = ? ");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(1, descricao);
		
		List<Produto> result = query.getResultList();
		
		if (result.isEmpty())
			return null;
		
		return result.get(0); 
	}

}

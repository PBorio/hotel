package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import repositorios.QuartoRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Categoria;
import domain.Quarto;

@Component
public class QuartoDAO extends DAO<Quarto> implements QuartoRepositorio {

	
	public QuartoDAO() {
		super(Quarto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> buscaPorCategoria(Categoria categoria) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Quarto q ");
		hql.append(" join fetch q.reservas reserva ");
		hql.append(" where q.categoria.id = ? ");
		
		Query query = getEntityManager().createQuery(hql.toString());
		query.setParameter(1, categoria.getId());
		
		return (List<Quarto>)query.getResultList();
	}
	
}

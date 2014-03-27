package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import repositorios.EstadiaRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Estadia;


@Component
public class EstadiaDAO extends DAO<Estadia> implements EstadiaRepositorio {

	public EstadiaDAO() {
		super(Estadia.class);
	}

	@SuppressWarnings("unchecked")
	public List<Estadia> estadiasAbertas() {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" Select e From Estadia e ");
		sql.append(" join fetch e.quarto q ");
		sql.append(" where e.dataCheckout is null ");
		sql.append(" and e.dataCancelamento is null ");
		sql.append(" order by q.numero ");
		
		Query query = entityManager.createQuery(sql.toString());
		return query.getResultList();
	}
}

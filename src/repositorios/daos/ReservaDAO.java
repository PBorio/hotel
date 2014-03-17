package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Reserva;

@Component
public class ReservaDAO extends DAO<Reserva> implements ReservaRepositorio {

	public ReservaDAO() {
		super(Reserva.class);
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscarPorNomeDoHospede(String pesquisa) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" Select r From Reserva r ");
		sql.append(" join r.hospede hospede ");
		sql.append(" where hospede.nome like ? ");
		sql.append(" order by r.id ");
		
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, "%"+pesquisa+"%");
		return query.getResultList();
	}
}

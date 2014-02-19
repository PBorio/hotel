package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import repositorios.HospedeRepositorio;
import domain.Hospede;

@Component
public class HospedeDAO extends DAO<Hospede> implements HospedeRepositorio {

	public HospedeDAO() {
		super(Hospede.class);
	}

	@SuppressWarnings("unchecked")
	public Hospede buscaPorEmail(String email) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Hospede h where h.email = ? ");
		Query q = getEntityManager().createQuery(hql.toString());
		q.setParameter(1, email);
		List<Hospede> hospedes = (List<Hospede>)q.getResultList();
		if (hospedes.size() == 0)
			return null;
		return hospedes.get(0);
	}


}

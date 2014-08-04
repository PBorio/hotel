package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import repositorios.ServicoRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Servico;

@Component
public class ServicoDAO extends DAO<Servico> implements ServicoRepositorio {

	public ServicoDAO() {
		super(Servico.class);
	}

	@SuppressWarnings("unchecked")
	public List<Servico> buscaServicosPorDescricao(String descricao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From Servico s ");
		hql.append(" where s.descricao like ? ");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(1, "%" + descricao + "%");
		
		List<Servico> result = query.getResultList();
		return result; 
	}

	@SuppressWarnings("unchecked")
	public Servico buscaPorEstaDescricao(String descricao) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From Servico s ");
		hql.append(" where s.descricao = ? ");

		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(1, descricao);
		
		List<Servico> result = query.getResultList();
		
		if (result.isEmpty())
			return null;
		
		return result.get(0); 
	}
}

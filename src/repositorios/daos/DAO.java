package repositorios.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class DAO<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		super();
		this.classe = classe;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T buscaPorId(Long id) {
		if (id == null) return null;
		return entityManager.find(classe, id);
	}

	@Transactional()
	public void salva(T t) {
		entityManager.persist(t);		
		
	}
	
	@Transactional()
	public void atualiza(T t) {
		entityManager.merge(t);		
		
	}

	@SuppressWarnings("unchecked")
	public List<T> buscaTodos(int pagina) {
		Query query = entityManager.createQuery(" From " + classe.getName() + " c order by c.id desc");
		query.setFirstResult(pagina*20);
		query.setMaxResults(20);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscaTodos(){
		Query query = entityManager.createQuery(" From " + classe.getName() + " c order by c.id desc");
		return query.getResultList();
	}

	public Long buscaTotalDeRegistrosDaLista() {
		return (Long) entityManager.createQuery("select count(c.id) From "+classe.getName()+" c").getSingleResult();
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}

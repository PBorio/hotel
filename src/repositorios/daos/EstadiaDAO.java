package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import repositorios.EstadiaRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.Consumo;
import domain.Estadia;
import domain.HospedeDaEstadia;
import domain.PagamentoEstadia;
import domain.ServicoPrestado;


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

	public HospedeDaEstadia buscaHospedeDaEstadiaPorId(Long id) {
		return getEntityManager().find(HospedeDaEstadia.class, id);
	}


	@Transactional
	public void excluirHospede(HospedeDaEstadia hospedeDaEstadia) {
		super.entityManager.remove(hospedeDaEstadia);
	}
	
	@Transactional
	@Override
	public void salva(Estadia estadia) {
		getEntityManager().persist(estadia);
		getEntityManager().flush();
		for (PagamentoEstadia pe : estadia.getPagamentosEstadias()){
			getEntityManager().persist(pe.getPagamento());
			getEntityManager().flush();
			getEntityManager().persist(pe);
		}
	}

	@Transactional
	public void salvarServicoPrestado(ServicoPrestado servicoPrestado) {
		getEntityManager().persist(servicoPrestado);
	}

	@Transactional
	public void salvarConsumo(Consumo consumo) {
		getEntityManager().persist(consumo);		
	}

	public Consumo buscarConsumoPorId(Long id) {
		return getEntityManager().find(Consumo.class, id);
	}

	@Transactional
	public void deletaConsumo(Consumo consumo) {
		getEntityManager().remove(consumo);
	}

	public ServicoPrestado buscarServicoPrestadoPorId(Long id) {
		return getEntityManager().find(ServicoPrestado.class, id);
	}

	@Transactional
	public void deletaServicoPrestado(ServicoPrestado servicoPrestado) {
		getEntityManager().remove(servicoPrestado);		
	}
}

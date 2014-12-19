package repositorios.daos;

import java.util.List;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.ioc.Component;
import domain.AgrupadorReservas;
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
		sql.append(" join fetch r.hospede hospede ");
		sql.append(" join fetch r.pagamentosReservas pagamentoReserva ");
		sql.append(" join fetch pagamentoReserva.pagamento pagamento ");
		sql.append(" where hospede.nome like ? ");
		sql.append(" order by r.id ");
		
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, "%"+pesquisa+"%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscaReservasEmAberto() {
		
		DateTime tresDiasAtras = new DateTime().minusDays(15);
				
		StringBuilder sql = new StringBuilder();
		sql.append(" Select r From Reserva r ");
		sql.append(" join fetch r.hospede hospede ");
		sql.append(" join fetch r.pagamentosReservas pagamentoReserva ");
		sql.append(" join fetch pagamentoReserva.pagamento pagamento ");
		sql.append(" where not exists (select 1 from Estadia e where e.reserva = r) ");
		sql.append(" and r.inicio > ? ");
		sql.append(" order by r.id ");
		
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, tresDiasAtras);
		return query.getResultList();
	}

	@Transactional
	public void salvarReservas(AgrupadorReservas agrupadorReservas) {
		entityManager.persist(agrupadorReservas);
		for (Reserva reserva : agrupadorReservas.getReservas()){
			super.salva(reserva);
		}
	}

	public Reserva buscaReservaPorIdComPagamentos(Long idReserva) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select r From Reserva r ");
		sql.append(" join fetch r.hospede hospede ");
		sql.append(" join fetch r.pagamentosReservas pagamentoReserva ");
		sql.append(" join fetch pagamentoReserva.pagamento pagamento ");
		sql.append(" where r.id = ? ");
		
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter(1, idReserva);
		return (Reserva) query.getSingleResult();
	}

	public AgrupadorReservas buscarAgrupadorPorId(Long idAgrupador) {
		return entityManager.find(AgrupadorReservas.class, idAgrupador);
	}
}

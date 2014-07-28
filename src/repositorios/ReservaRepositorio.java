package repositorios;

import java.util.List;

import domain.Reserva;
public interface ReservaRepositorio {
	
	List<Reserva> buscaTodos();
	
	public Reserva buscaPorId(Long id);
	
	void salva(Reserva reserva);
	
	void atualiza(Reserva reserva);

	List<Reserva> buscarPorNomeDoHospede(String pesquisa);

	List<Reserva> buscaReservasEmAberto();

	void salvarVariasReservas(List<Reserva> reservas);

	Reserva buscaReservaPorIdComPagamentos(Long idReserva);

}

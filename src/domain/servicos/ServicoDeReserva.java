package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import domain.Quarto;
import domain.Reserva;

public class ServicoDeReserva {

	private List<Quarto> quartos;

	public ServicoDeReserva(List<Quarto> quartos) {
		this.quartos = quartos;
	}

	public Quarto quartoDisponivelParaAReserva(Reserva reserva) {
		for (Quarto q : quartos){
			if (!q.possuiReservasNoMesmoPeriodo(reserva)){
				return q;
			}
		}
		return null;
	}

	public List<Quarto> quartosDisponiveisParaAReserva(Reserva reserva) {
		List<Quarto> disponiveis = new ArrayList<Quarto>();
		for (Quarto q : quartos){
			if (!q.possuiReservasNoMesmoPeriodo(reserva)){
				disponiveis.add(q);
			}
		}
		return disponiveis;
	}
}

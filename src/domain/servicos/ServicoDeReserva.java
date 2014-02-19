package domain.servicos;

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

//	public boolean qua(DateTime carnaval) {
//		
//		
//		if (reservas == null || reservas.size() == 0)
//			return true;
//		
//		return false;
//	}

}

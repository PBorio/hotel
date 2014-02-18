package domain.servicos;

import java.util.List;

import org.joda.time.DateTime;

import domain.Reserva;

public class ServicoDeReserva {

	private List<Reserva> reservas;

	public ServicoDeReserva(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public boolean disponivelNaData(DateTime carnaval) {
		if (reservas == null || reservas.size() == 0)
			return true;
		
		return false;
	}

}

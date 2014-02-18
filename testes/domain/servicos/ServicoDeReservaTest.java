package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import domain.Reserva;

public class ServicoDeReservaTest {
	
	@Test
	public void seNaoHaNenhumaReservaOServicoDeReservaIndicaDisponivelNaData(){
		DateTime carnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		ServicoDeReserva servico = new ServicoDeReserva(reservas);

		Assert.assertTrue(servico.disponivelNaData(carnaval));
	}
	
	@Ignore
	public void seADataCaiNumDiaQueJaTemReservaIndicaReservadoNaData(){

		DateTime carnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime primeiroDeMarco = new DateTime(2014,3,1,0,0,0);
		DateTime quinzeDeMarco = new DateTime(2014,3,15,0,0,0);
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		Reserva reserva = new Reserva();
		reserva.setInicio(primeiroDeMarco);
		reserva.setFim(quinzeDeMarco);
		
		ServicoDeReserva servico = new ServicoDeReserva(reservas);
		Assert.assertFalse(servico.disponivelNaData(carnaval));
	}
	

}

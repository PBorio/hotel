package domain.servicos;

import org.junit.Assert;
import org.junit.Test;

import domain.Estadia;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;
import domain.helpers.FakeReserva;

public class CheckinTest {
	
	@Test
	public void oCheckinVaiCriarUmaEstadiaComBaseNosDadosDaReserva(){
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").build();
		reserva.setHospede(new Hospede());
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva);
		Estadia estadia = checkin.iniciarEstadia();
		Assert.assertNotNull(estadia.getHospedes().get(0));
	}
	
	@Test
	public void umDosHospedesDaEstadiaSeraOHospedeDaReserva(){
		Hospede joao = new Hospede();
		joao.setNome("Joao");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").build();
		reserva.setHospede(joao);
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva);
		Estadia estadia = checkin.iniciarEstadia();
		Assert.assertEquals("Joao", estadia.getHospedes().get(0).getNome());
	}
	
	@Test
	public void oQuartoDaEstadiaSeraOQuartoDaReserva(){
		
		Quarto _101 = new Quarto();
		_101.setNumero("101");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").build();
		reserva.setHospede(new Hospede());
		reserva.setQuarto(_101);
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva);
		Estadia estadia = checkin.iniciarEstadia();
		Assert.assertEquals("101", estadia.getQuarto().getNumero());
	}

}

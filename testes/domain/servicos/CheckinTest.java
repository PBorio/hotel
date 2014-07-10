package domain.servicos;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.Estadia;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;
import domain.helpers.FakeReserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class CheckinTest {
	
	@Test
	public void oCheckinVaiCriarUmaEstadiaParaUmaReservaEUmQuarto(){
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").noQuarto("1").build();
		
		Quarto quarto = reserva.getQuarto();
		
		reserva.setHospede(new Hospede());
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva, quarto);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertNotNull(estadia);
	}
	
	@Test
	public void umDosHospedesDaEstadiaSeraOHospedeDaReserva(){
		Hospede joao = new Hospede();
		joao.setNome("Joao");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").noQuarto("1").build();
		reserva.setHospede(joao);
		
		Quarto quarto = reserva.getQuarto();
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva, quarto);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals("Joao", estadia.getHospedes().get(0).getNome());
	}
	
	@Test
	public void oQuartoDaEstadiaSeraOQuartoDaReserva(){
		
		Quarto _101 = new Quarto();
		_101.setNumero("101");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(_101).build();
		reserva.setHospede(new Hospede());
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva, _101);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals("101", estadia.getQuarto().getNumero());
		
		
	}
	
	@Test
	public void oValorDaDiariaDaEstadiaSeraODaReserva(){
		
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(new Quarto()).build();
		reserva.setHospede(new Hospede());
		reserva.setValorDiaria(10.0);
		
		Quarto quarto = reserva.getQuarto();
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva, quarto);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals((Double)10.0, estadia.getValorDiaria());
		
	}
	
	@Test
	public void umaEstadiaTambemPodeSerCriadaSemUmaReservaInformandoHospedeQuartoEDataDeCheckin(){
		
		Quarto _101 = new Quarto();
		_101.setNumero("101");
		
		Hospede joao = new Hospede();
		joao.setNome("Joao");
		
		DateTime dataCheckin = new ParserDeStringParaData().parseData("11/03/2014");
		
		Checkin checkin = Checkin.checkinSemReserva(_101, joao, dataCheckin, 10.0);
		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		Assert.assertEquals("Joao", estadia.getHospedes().get(0).getNome());
		Assert.assertEquals("101", estadia.getQuarto().getNumero());
		Assert.assertEquals(dataCheckin, estadia.getDataCheckin());
		
	}

}

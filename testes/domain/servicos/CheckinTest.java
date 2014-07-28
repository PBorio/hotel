package domain.servicos;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.Estadia;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HospedeInvalidoException;
import domain.exceptions.QuartoInvalidoException;
import domain.exceptions.ReservaInvalidaException;
import domain.helpers.FakeReserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class CheckinTest {
	
	@Test
	public void oCheckinVaiCriarUmaEstadiaParaUmaReservaEUmQuarto(){
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").noQuarto("1").comValorDaDiariaDe(10.0).build();
		reserva.setHospede(new Hospede());
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertNotNull(estadia);
	}
	
	@Test
	public void osHospedesDoCheckinVaoParaAEstadia(){
		Hospede joao = new Hospede();
		joao.setNome("Joao");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").noQuarto("1").comValorDaDiariaDe(10.0).build();
		reserva.setHospede(joao);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(joao);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals(joao, estadia.getHospedes().get(0));
	}
	
	@Test(expected=ReservaInvalidaException.class)
	public void aoIniciarOCheckinAPartirDeUmaReservaAReservaNaoPodeEstarNula(){
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(null);
		checkin.iniciarEstadiaAPartirDeUmaReserva();
	}
	
	@Test(expected=QuartoInvalidoException.class)
	public void aoIniciarOCheckinAPartirDeUmaReservaOQuartoNaoPodeSerNulo(){
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").build();
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.iniciarEstadiaAPartirDeUmaReserva();
	}
	
	@Test(expected=HospedeInvalidoException.class)
	public void aoIniciarOCheckinDeveTerPeloMenosUmHospedeInformado(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOHospede("Joao").noQuarto("1").build();
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.iniciarEstadiaAPartirDeUmaReserva();
	}
	
	@Test
	public void umHospedePodeSerRemovidoDosHospedesDoCheckin(){
		Hospede joao = new Hospede();
		joao.setId(1l);
		
		Hospede maria = new Hospede();
		maria.setId(2l);
		
		Checkin checkin = new Checkin();
		checkin.addHospede(joao);
		checkin.addHospede(maria);
		
		Assert.assertEquals(2, checkin.getHospedes().size());
		
		checkin.removeHospede(joao);
		
		Assert.assertEquals(1, checkin.getHospedes().size());
		Assert.assertFalse(checkin.getHospedes().contains(joao));
	}
	
	@Test
	public void oQuartoDaEstadiaSeraOQuartoDaReserva(){
		
		Quarto _101 = new Quarto();
		_101.setNumero("101");
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(_101).comValorDaDiariaDe(10.0).build();
		reserva.setHospede(new Hospede());
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals("101", estadia.getQuarto().getNumero());
		
		
	}
	
	@Test
	public void seNaoHouverDiferencaNasDatasNoCheckinoValorDaDiariaDaEstadiaSeraODaReserva(){
		
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(new Quarto()).build();
		reserva.setHospede(new Hospede());
		reserva.setValorDiaria(10.0);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals((Double)10.0, estadia.getValorDiaria());
		
	}
	
	@Test
	public void seOValorDaDiariaNoCheckinForAlteradaDeveSerFeitoORecalculoDosValores(){
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(new Quarto()).build();
		reserva.setHospede(new Hospede());
		reserva.setValorDiaria(10.0);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.setValorDiaria(15.0);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals((Double)15.0, estadia.getValorDiaria());
		
	}
	
	@Test
	public void oCheckinPodeTerUmValorDeDescontoNaDiaria(){
		
		Reserva reserva = new FakeReserva().iniciandoEm("10/03/2014").terminandoEm("15/03/2014").paraOQuarto(new Quarto()).build();
		reserva.setHospede(new Hospede());
		reserva.setValorDiaria(10.0);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.setValorDiaria(15.0);
		checkin.setDesconto(10.0);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		Assert.assertEquals((Double)13.5, estadia.getValorDiaria());
		
	}
	
	@Test
	public void umaEstadiaTambemPodeSerCriadaSemUmaReservaInformandoHospedeQuartoEDataDeCheckin(){
		
		Quarto _101 = new Quarto();
		_101.setNumero("101");
		
		Hospede joao = new Hospede();
		joao.setNome("Joao");
		
		DateTime dataCheckin = new ParserDeStringParaData().parseData("11/03/2014");
		
		Checkin checkin = new Checkin();
		checkin.setQuarto(_101);
		checkin.addHospede(joao);
		checkin.setValorDiaria(10.0);
		checkin.setDataCheckin(dataCheckin.toDate());
		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		Assert.assertEquals("Joao", estadia.getHospedes().get(0).getNome());
		Assert.assertEquals("101", estadia.getQuarto().getNumero());
		Assert.assertEquals(dataCheckin, estadia.getDataCheckin());
		
	}

}

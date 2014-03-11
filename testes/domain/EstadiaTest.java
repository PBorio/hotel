package domain;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;
import domain.servicos.Checkin;
import domain.servicos.helpers.ParserDeStringParaData;

public class EstadiaTest {
	
	
	@Test
	public void umaEstadiaEstaAbertaSeNaoTiverDataDeCheckout(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		Assert.assertTrue(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaEstaFechadaSeTiverDataDeCheckout(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		Assert.assertFalse(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaEstaNaoEstaAbertaSeEstiverCancelada(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.cancelar(new ParserDeStringParaData().parseData("09/03/2014"));
		Assert.assertFalse(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaNaoEstaFechadaSeTiverDataDeCheckoutMasTambemDeCancelamento(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		estadia.cancelar(new ParserDeStringParaData().parseData("11/03/2014"));
		Assert.assertFalse(estadia.isFechada());
		Assert.assertTrue(estadia.isCancelada());
	}
	
	@Test
	public void oValorDeUmaEstadiaEmAbertoComReservaEhOValorDaDiariaDaReservaAteADataInformada(){
		Reserva reserva = new FakeReserva().iniciandoEm("09/03/2014").terminandoEm("11/03/2014").paraOHospede("Joao").noQuarto("19").build();
		reserva.setValorDiaria(10.0);
		
		Checkin checkin = Checkin.checkinAPartirDeUmaReserva(reserva);
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		DateTime hoje = new ParserDeStringParaData().parseData("11/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}
	
	@Test
	public void casoAEstadiaNaoTenhaSidoCriadaAtravesDeUmaReservaElaMesmaTeraQueInformarOValorDaDiaria(){
		
		Checkin checkin = Checkin.checkinSemReserva(new Quarto(), new Hospede(), new ParserDeStringParaData().parseData("09/03/2014"), 10.0);
		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		
		DateTime hoje = new ParserDeStringParaData().parseData("11/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}
	
	
	@Test
	public void casoAEstadiaJaEstejaFechadaOValorCalculadoEhSomenteAteADataDeFechamento(){
		
		Checkin checkin = Checkin.checkinSemReserva(new Quarto(), new Hospede(), new ParserDeStringParaData().parseData("09/03/2014"), 10.0);
		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		
		DateTime hoje = new ParserDeStringParaData().parseData("14/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}

}

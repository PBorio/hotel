package domain;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;

public class ReservaTest {

	@Test
	public void seADataCaiNoPeriodoEntreInicioEFimDaReservaIndicaReservado(){

		DateTime carnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime primeiroDeMarco = new DateTime(2014,3,1,0,0,0);
		DateTime quinzeDeMarco = new DateTime(2014,3,15,0,0,0);
		
		Reserva reserva = new Reserva();
		reserva.setInicio(primeiroDeMarco);
		reserva.setFim(quinzeDeMarco);
		
		Assert.assertTrue(reserva.contemAData(carnaval));
	}
	
	@Test
	public void seADataCaiNumPeriodoForaDoInicioEFimDaReservaIndicaNaoReservado(){

		DateTime carnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime dezessesisDeMarco = new DateTime(2014,3,16,0,0,0);
		DateTime trintaDeMarco = new DateTime(2014,3,30,0,0,0);
		
		Reserva reserva = new Reserva();
		reserva.setInicio(dezessesisDeMarco);
		reserva.setFim(trintaDeMarco);
		
		Assert.assertFalse(reserva.contemAData(carnaval));
	}
	
	@Test
	public void umaReservaDeveSaberSeOutraReservaEhParaOMesmoPeriodo(){
		
		DateTime sabadoCarnaval = new DateTime(2014,3, 1,0,0,0);
		DateTime tercaCarnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime primeiroDeMarco = new DateTime(2014,3,1,0,0,0);
		DateTime trintaDeMarco = new DateTime(2014,3,30,0,0,0);
		
		Reserva reservaMarco = new Reserva();
		reservaMarco.setInicio(primeiroDeMarco);
		reservaMarco.setFim(trintaDeMarco);
		
		Reserva reservaCarnaval = new Reserva();
		reservaCarnaval.setInicio(sabadoCarnaval);
		reservaCarnaval.setFim(tercaCarnaval);
		
		Assert.assertTrue(reservaMarco.coincideCom(reservaCarnaval));
		
	}
	
	@Test
	public void umaReservaDeveSaberSeOutraReservaNaoEhParaOMesmoPeriodo(){
		
		DateTime sabadoCarnaval = new DateTime(2014,3, 1,0,0,0);
		DateTime tercaCarnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime dezessesisDeMarco = new DateTime(2014,3,16,0,0,0);
		DateTime trintaDeMarco = new DateTime(2014,3,30,0,0,0);
		
		Reserva reservaMarco = new Reserva();
		reservaMarco.setInicio(dezessesisDeMarco);
		reservaMarco.setFim(trintaDeMarco);
		
		Reserva reservaCarnaval = new Reserva();
		reservaCarnaval.setInicio(sabadoCarnaval);
		reservaCarnaval.setFim(tercaCarnaval);
		
		Assert.assertFalse(reservaMarco.coincideCom(reservaCarnaval));
	}
	
	@Test
	public void seUmaReservaComecaAntesDeOutraETerminaDepoisDeveConsiderarQueCoincide(){
		DateTime sabadoCarnaval = new DateTime(2014,3, 1,0,0,0);
		DateTime tercaCarnaval = new DateTime(2014, 3, 4, 0, 0, 0);
		DateTime primeiroDeFevereiro = new DateTime(2014,2,1,0,0,0);
		DateTime trintaDeMarco = new DateTime(2014,3,30,0,0,0);
		
		Reserva reservaMarco = new Reserva();
		reservaMarco.setInicio(primeiroDeFevereiro);
		reservaMarco.setFim(trintaDeMarco);
		
		Reserva reservaCarnaval = new Reserva();
		reservaCarnaval.setInicio(sabadoCarnaval);
		reservaCarnaval.setFim(tercaCarnaval);
		
		Assert.assertTrue(reservaCarnaval.coincideCom(reservaMarco));
	}
	
	@Test
	public void seOUltimoDiaDeUmaReservaEhIgualAoPrimeiroDiaDeOutraEntaoElasCoincidem(){
		DateTime sabado = new DateTime(2014,7,19,0,0,0);
		DateTime terca1 = new DateTime(2014,7,22,0,0,0);
		DateTime terca2 = new DateTime(2014,7,22,0,0,0);
		DateTime sexta  = new DateTime(2014,7,25,0,0,0);
		
		Reserva primeiraReserva = new Reserva();
		primeiraReserva.setInicio(sabado);
		primeiraReserva.setFim(terca1);
		
		Reserva segundaReserva = new Reserva();
		segundaReserva.setInicio(terca2);
		segundaReserva.setFim(sexta);
		
		Assert.assertTrue(primeiraReserva.coincideCom(segundaReserva));
	}
	
	@Test
	public void oValorDaReservaEhValorDaDiariaVezesONumeroDeDiasDaReserva(){
		Reserva r = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("04/03/2014").comNumeroDeAdultos(2).build();
		r.setValorDiaria(10.2);
		
		Assert.assertEquals((Double)30.6, r.getValorReserva());
	}
	
	@Test
	public void oValorDaReservaPodeSerAlteradoManualmente(){
		Reserva r = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("04/03/2014").comNumeroDeAdultos(2).build();
		r.setValorDiaria(10.2);
		Assert.assertEquals((Double)30.6, r.getValorReserva());
		r.setValorReserva(40.0);
		Assert.assertEquals((Double)40.0, r.getValorReserva());
	}

	@Test
	public void aReservaEhSoParaUmAdultoSeTiverApenasUmAdulto(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		Assert.assertTrue(r.isSoParaUmAdulto());
	}
	
	@Test
	public void aReservaNaoEhSoParaUmAdultoSeNaoTiverNenhum(){
		Reserva r = new FakeReserva().build();
		Assert.assertFalse(r.isSoParaUmAdulto());
	}
	
	@Test
	public void aReservaNaoEhSoParaUmAdultoSeTiverMaisDeUm(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(2).build();
		Assert.assertFalse(r.isSoParaUmAdulto());
	}
	
	@Test
	public void seHouverUmaCriancaDe0a5NaoEhSoParaUmAdulto(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas0a5(1);
		Assert.assertFalse(r.isSoParaUmAdulto());
	}
	
	@Test
	public void seHouverUmaCriancaDe6a16NaoEhSoParaUmAdulto(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas6a16(1);
		Assert.assertFalse(r.isSoParaUmAdulto());
	}
	
	@Test
	public void seHouverUmaCriancaDe17a18NaoEhSoParaUmAdulto(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas17a18(1);
		Assert.assertFalse(r.isSoParaUmAdulto());
	}
	
	@Test
	public void umaCriancaDeZeroA5NaoFormaNenhumPar(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas0a5(1);
		Assert.assertEquals(0, r.getParDeCriancasDe0a5());
	}
	
	@Test
	public void duasCriancasDeZeroA5FormaUmPar(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas0a5(2);
		Assert.assertEquals(1, r.getParDeCriancasDe0a5());
	}
	
	@Test
	public void tresCriancasDeZeroA5NaoFormaDoisParesEntaoConsidera1(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		r.setNumeroCriancas0a5(3);
		Assert.assertEquals(1, r.getParDeCriancasDe0a5());
	}
	
	@Test
	public void nenhumaCriancaDe0a5NaoFormaPar(){
		Reserva r = new FakeReserva().comNumeroDeAdultos(1).build();
		Assert.assertEquals(0, r.getParDeCriancasDe0a5());
	}
}


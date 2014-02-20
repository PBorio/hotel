package domain;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class ReservaTest {

	@Test
	public void umPeriodoDeveSaberSeEstaContidoEmOutro() {
		DateTime inicio = new DateTime(2014, 1, 1, 0, 0, 0);
		DateTime fim = new DateTime(2014, 1 , 15, 0, 0, 0);
		
		Interval primeiraQuinzenaDeJaneiro = new Interval(inicio, fim);
		
		DateTime primeiroDeJaneiro = new DateTime(2014, 1, 1, 0, 0, 0);
		DateTime trintaEUmDeJaneiro = new DateTime(2014, 1 , 31, 0, 0, 0);
		
		Interval janeiro = new Interval(primeiroDeJaneiro, trintaEUmDeJaneiro);
		
		Assert.assertTrue(janeiro.contains(primeiraQuinzenaDeJaneiro));
		Assert.assertFalse(primeiraQuinzenaDeJaneiro.contains(janeiro));
	}
	
	@Test
	public void oPrimeiroTrimestreContemOMesDeFevereiros() {
		DateTime inicio = new DateTime(2014, 2, 1, 0, 0, 0);
		DateTime fim = new DateTime(2014, 2 , 28, 0, 0, 0);
		
		Interval fevereiro = new Interval(inicio, fim);
		
		DateTime primeiroDeJaneiro = new DateTime(2014, 1, 1, 0, 0, 0);
		DateTime trintaEUmDeMarco = new DateTime(2014, 3 , 31, 0, 0, 0);
		
		Interval primeiroTrimestre = new Interval(primeiroDeJaneiro, trintaEUmDeMarco);
		
		Assert.assertTrue(primeiroTrimestre.contains(fevereiro));
		Assert.assertFalse(fevereiro.contains(primeiroTrimestre));
	}
	
	@Test
	public void fevereiroNaoContemDePeriodoDe15DeFevereiroA15DeMarco() {
		DateTime inicio = new DateTime(2014, 2, 15, 0, 0, 0);
		DateTime fim = new DateTime(2014, 3 , 15, 0, 0, 0);
		
		Interval periodo = new Interval(inicio, fim);
		
		DateTime primeiroDeFevereiro = new DateTime(2014, 2, 1, 0, 0, 0);
		DateTime vinteEOitoDeFevereiro = new DateTime(2014, 2 , 28, 0, 0, 0);
		
		Interval fevereiro = new Interval(primeiroDeFevereiro, vinteEOitoDeFevereiro);
		
		Assert.assertFalse(fevereiro.contains(periodo));
		Assert.assertFalse(periodo.contains(fevereiro));
	}
	
	@Test
	public void fevereiroOverlapsPeriodoDe15DeFevereiroA15DeMarco() {
		DateTime inicio = new DateTime(2014, 2, 15, 0, 0, 0);
		DateTime fim = new DateTime(2014, 3 , 15, 0, 0, 0);
		
		Interval periodo = new Interval(inicio, fim);
		
		DateTime primeiroDeFevereiro = new DateTime(2014, 2, 1, 0, 0, 0);
		DateTime vinteEOitoDeFevereiro = new DateTime(2014, 2 , 28, 0, 0, 0);
		
		Interval fevereiro = new Interval(primeiroDeFevereiro, vinteEOitoDeFevereiro);
		
		Assert.assertTrue(fevereiro.overlaps(periodo));
		Assert.assertTrue(periodo.overlaps(fevereiro));
	}
	
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
	public void oValorDaReservaEhValorDaDiariaVezesONumeroDeDiasDaReserva(){
		Reserva r = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("04/03/2014").build();
		r.setValorDiaria(10.2);
		
		Assert.assertEquals((Double)30.6, r.getValorReserva());
	}

}


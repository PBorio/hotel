package domain;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Test;

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

}


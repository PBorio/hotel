package domain;

import org.junit.Assert;
import org.junit.Test;

public class PoliticaDePrecosTest {
	
	@Test
	public void umaPoliticaDePrecosDeveSaberQuePertenceAMesmaCategoria(){
		Categoria umaCategoria = new Categoria();
		umaCategoria.setId(1l);
		
		PoliticaDePrecos politicaPadrao = new PoliticaDePrecos();
		politicaPadrao.setCategoria(umaCategoria);
		politicaPadrao.setPadrao(true);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(umaCategoria);
		
		Assert.assertTrue(outraPolitica.possuiAMesmaCategoria(politicaPadrao));
		
	}
	
	@Test
	public void politicasComCategoriasDiferentesNaoPertencemAMesmaCategoria(){
		Categoria umaCategoria = new Categoria();
		umaCategoria.setId(1l);
		
		PoliticaDePrecos politicaPadrao = new PoliticaDePrecos();
		politicaPadrao.setCategoria(umaCategoria);
		politicaPadrao.setPadrao(true);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(new Categoria());
		
		Assert.assertFalse(outraPolitica.possuiAMesmaCategoria(politicaPadrao));
		
	}

}

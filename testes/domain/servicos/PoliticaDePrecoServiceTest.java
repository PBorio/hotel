package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Categoria;
import domain.PoliticaDePrecos;
import domain.exceptions.PoliticaComDataCoincidenteException;
import domain.exceptions.PoliticaPadraoJaExistenteException;
import domain.servicos.helpers.ParserDeStringParaData;

public class PoliticaDePrecoServiceTest {
	
	private List<PoliticaDePrecos> politicasExistentes = new ArrayList<PoliticaDePrecos>();
	
	@Test(expected=PoliticaPadraoJaExistenteException.class)
	public void umaPoliticaPadraoNaoEhValidaSeForPadraoEJaHouverOutraPadraoParaACategoria(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		PoliticaDePrecos politicaPadrao = new PoliticaDePrecos();
		politicaPadrao.setCategoria(categoria);
		politicaPadrao.setPadrao(true);
		politicaPadrao.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadrao.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadrao);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoria);
		outraPolitica.setPadrao(true);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		politicaService.validar(outraPolitica);
	}
	
	@Test
	public void umaPoliticaPadraoEhValidaSeNaoOutraPadraoParaACategoria(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoria);
		outraPolitica.setPadrao(true);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test
	public void umaPoliticaPadraoEhValidaSeHouverOutraNaoPadraoParaACategoria(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		PoliticaDePrecos politicaNaoPadrao = new PoliticaDePrecos();
		politicaNaoPadrao.setCategoria(categoria);
		politicaNaoPadrao.setPadrao(false);
		politicaNaoPadrao.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaNaoPadrao.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaNaoPadrao);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoria);
		outraPolitica.setPadrao(true);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test
	public void umaPoliticaPadraoEhValidaSeHouverOutraPadraoMasParaOutraCategoria(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		Categoria categoriaDois = new Categoria();
		categoriaDois.setId(2l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setPadrao(true);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaDois);
		outraPolitica.setPadrao(true);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test
	public void umaPoliticaNaoPadraoEhValidaMesmoQueHajaUmaPadraoParaACategoria(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setPadrao(true);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaUm);
		outraPolitica.setPadrao(false);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test(expected=PoliticaComDataCoincidenteException.class)
	public void umaPoliticaNaoPadraoNaoEhValidaSeOPeriodoCoincidirComOutraNaoPadrao(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setPadrao(false);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaUm);
		outraPolitica.setPadrao(false);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		politicaService.validar(outraPolitica);
	}

}

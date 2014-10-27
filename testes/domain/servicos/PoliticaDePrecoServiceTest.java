package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Categoria;
import domain.PoliticaDePrecos;
import domain.exceptions.PoliticaComDataCoincidenteException;
import domain.servicos.helpers.ParserDeStringParaData;

public class PoliticaDePrecoServiceTest {
	
	private List<PoliticaDePrecos> politicasExistentes = new ArrayList<PoliticaDePrecos>();
	
	@Test
	public void umaPoliticaEhValidaSeNaoHouverOutraParaACategoria(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoria);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test
	public void umaPoliticaEhValidaSeHouverOutraMasParaOutraCategoria(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		Categoria categoriaDois = new Categoria();
		categoriaDois.setId(2l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaDois);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		Assert.assertTrue(politicaService.validar(outraPolitica));
	}
	
	@Test
	public void umaPoliticaEhValidaSeOPeriodoNaoCoincidirComOutraEForDaMesmaCategoria(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaUm);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/09/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/09/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		politicaService.validar(outraPolitica);
	}
	
	@Test
	public void seAPoliticaForAMesmaADataPodeCoincidir(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setId(1l);
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setId(1l);
		outraPolitica.setCategoria(categoriaUm);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		politicaService.validar(outraPolitica);
	}
	
	@Test(expected=PoliticaComDataCoincidenteException.class)
	public void umaPoliticaNaoEhValidaSeOPeriodoCoincidirComOutra(){
		Categoria categoriaUm = new Categoria();
		categoriaUm.setId(1l);
		
		PoliticaDePrecos politicaPadraoDaCagegoriaUm = new PoliticaDePrecos();
		politicaPadraoDaCagegoriaUm.setCategoria(categoriaUm);
		politicaPadraoDaCagegoriaUm.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		politicaPadraoDaCagegoriaUm.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		politicasExistentes.add(politicaPadraoDaCagegoriaUm);
		
		PoliticaDePrecos outraPolitica = new PoliticaDePrecos();
		outraPolitica.setCategoria(categoriaUm);
		outraPolitica.setInicio(new ParserDeStringParaData().parseData("08/08/2014").toDate());
		outraPolitica.setFim(new ParserDeStringParaData().parseData("12/08/2014").toDate());
		
		PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
		politicaService.validar(outraPolitica);
	}

}

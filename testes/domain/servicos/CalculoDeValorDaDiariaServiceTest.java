package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Categoria;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.helpers.FakeReserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class CalculoDeValorDaDiariaServiceTest {
	
	private ParserDeStringParaData parser = new ParserDeStringParaData();
	
	@Test
	public void oValorDaReservaEhDeAcordoComAPoliticaDePrecosDaCategoriaDosQuartos(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/04/2014").terminandoEm("05/04/2014").paraOQuarto(quarto).build();
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("01/04/2014").toDate());
		politica.setFim(parser.parseData("30/04/2014").toDate());
		politica.setValorDiaria(50.0);
		
		List<PoliticaDePrecos> politicas = new ArrayList<PoliticaDePrecos>();
		politicas.add(politica);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)50.0, reserva.getValorDiaria());
	}
	
	@Test
	public void seNenhumaPoliticaForEncontradaParaOPeriodoValorDaReservaEhAPoliticaPadraoIndependenteDePeriodo(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/04/2014").terminandoEm("05/04/2014").paraOQuarto(quarto).build();
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("06/04/2014").toDate());
		politica.setFim(parser.parseData("30/04/2014").toDate());
		politica.setValorDiaria(50.0);
		
		PoliticaDePrecos padrao = new PoliticaDePrecos();
		padrao.setCategoria(categoria);
		padrao.setInicio(parser.parseData("01/04/2013").toDate());
		padrao.setFim(parser.parseData("31/12/2013").toDate());
		padrao.setValorDiaria(40.0);
		padrao.setPadrao(true);
		
		List<PoliticaDePrecos> politicas = new ArrayList<PoliticaDePrecos>();
		politicas.add(politica);
		politicas.add(padrao);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)40.0, reserva.getValorDiaria());
	}
	
	@Test(expected=RuntimeException.class)
	public void seNenhumaPoliticaForEncontradaParaOPeriodoValorENaoHouverUmaPadraoDeveLancarExcecao(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/04/2014").terminandoEm("05/04/2014").paraOQuarto(quarto).build();
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("06/04/2014").toDate());
		politica.setFim(parser.parseData("30/04/2014").toDate());
		politica.setValorDiaria(50.0);
		
		PoliticaDePrecos padrao = new PoliticaDePrecos();
		padrao.setCategoria(categoria);
		padrao.setInicio(parser.parseData("01/04/2013").toDate());
		padrao.setFim(parser.parseData("31/12/2013").toDate());
		padrao.setValorDiaria(40.0);
		padrao.setPadrao(false);
		
		List<PoliticaDePrecos> politicas = new ArrayList<PoliticaDePrecos>();
		politicas.add(politica);
		politicas.add(padrao);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)40.0, reserva.getValorDiaria());
	}
	
	@Test
	public void havendoUmaPoliticaPadraoEOutraParaOPeriodoDaReservaOPrecoConsideradoEhODaReserva(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/04/2014").terminandoEm("05/04/2014").paraOQuarto(quarto).build();
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("01/04/2014").toDate());
		politica.setFim(parser.parseData("30/04/2014").toDate());
		politica.setValorDiaria(50.0);
		
		PoliticaDePrecos padrao = new PoliticaDePrecos();
		padrao.setCategoria(categoria);
		padrao.setInicio(parser.parseData("01/04/2013").toDate());
		padrao.setFim(parser.parseData("31/12/2013").toDate());
		padrao.setValorDiaria(40.0);
		padrao.setPadrao(true);
		
		List<PoliticaDePrecos> politicas = new ArrayList<PoliticaDePrecos>();
		politicas.add(politica);
		politicas.add(padrao);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)50.0, reserva.getValorDiaria());
	}

}

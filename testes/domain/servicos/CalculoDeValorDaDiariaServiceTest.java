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
import domain.servicos.helpers.Periodo;

public class CalculoDeValorDaDiariaServiceTest {
	
	private ParserDeStringParaData parser = new ParserDeStringParaData();
	List<PoliticaDePrecos> politicas = new ArrayList<PoliticaDePrecos>();
	
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
		
		politicas.add(politica);
		politicas.add(padrao);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)50.0, reserva.getValorDiaria());
	}

	@Test
	public void aoReceberUmQuartoEUmPeriodoParaCalculoOCalculoDeValorDaDiariaDeveCalcularOValorDaDiariaDaqueleQuartoParaOPeriodo(){
		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		Periodo periodo = new Periodo(parser.parseData("01/04/2014"), parser.parseData("05/04/2014"));
		
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
		
		politicas.add(politica);
		politicas.add(padrao);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		Double valorDiaria = servico.calcularValorDaDiaria(periodo, quarto);
		
		Assert.assertEquals((Double)50.0, valorDiaria);
	}
	
	@Test
	public void seUmaReservaForDeApenasUmAdultoOValorDaReservaSera60PorCentoDoValorCalculado(){

		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("01/01/2014").toDate());
		politica.setFim(parser.parseData("30/12/2014").toDate());
		politica.setValorDiaria(100.0);
		
		politicas.add(politica);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(1);
		reserva.setQuarto(quarto);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)60.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaParDeCriancasDe0a5DeveAcrescentar40ReaisNoValorDaDiaria(){

		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("01/01/2014").toDate());
		politica.setFim(parser.parseData("30/12/2014").toDate());
		politica.setValorDiaria(100.0);
		
		politicas.add(politica);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(1);
		reserva.setNumeroCriancas0a5(4);
		reserva.setQuarto(quarto);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)180.0, reserva.getValorDiaria());
	}

	@Test
	public void paraCadaParDeCriancasDe6a16DeveAcrescentar70ReaisNoValorDaDiaria(){

		Categoria categoria = new Categoria();
		categoria.setId(1l);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCategoria(categoria);
		
		PoliticaDePrecos politica = new PoliticaDePrecos();
		politica.setCategoria(categoria);
		politica.setInicio(parser.parseData("01/01/2014").toDate());
		politica.setFim(parser.parseData("30/12/2014").toDate());
		politica.setValorDiaria(100.0);
		
		politicas.add(politica);
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(2);
		reserva.setNumeroCriancas6a16(1);
		reserva.setQuarto(quarto);
		
		CalculoDeValorDaDiariaService servico = new CalculoDeValorDaDiariaService(politicas);
		servico.calcularEInformarValorNaReserva(reserva);
		
		Assert.assertEquals((Double)170.0, reserva.getValorDiaria());
	}
}

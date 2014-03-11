package domain.servicos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import domain.exceptions.ValorDiariaNaoInformadoException;
import domain.interfaces.CalculavelPorPeriodo;
import domain.servicos.helpers.ParserDeStringParaData;

public class CalculoDeValorDoPeriodoServiceTest {
	
	@Test
	public void umCalculavelPorDiariaTemSeuValorComBaseNaMultplicacaoDosDiasPeloValorUnitario(){
		CalculavelPorPeriodo calculavel = mock(CalculavelPorPeriodo.class);
		
		when(calculavel.getInicio()).thenReturn(new ParserDeStringParaData().parseData("09/03/2014"));
		when(calculavel.getFim()).thenReturn(new ParserDeStringParaData().parseData("11/03/2014"));
		when(calculavel.getValorDiaria()).thenReturn(10.0);
		
		CalculoDeValorPorPeriodoService calculoPorPeriodoService = new CalculoDeValorPorPeriodoService();
		Assert.assertEquals((Double)20.0, calculoPorPeriodoService.calcularValor(calculavel));
	}
	
	@Test(expected=ValorDiariaNaoInformadoException.class)
	public void seOValorDaDiariaNaoFoiInformadoDeveGerarExcecao(){
		CalculavelPorPeriodo calculavel = mock(CalculavelPorPeriodo.class);
		
		when(calculavel.getInicio()).thenReturn(new ParserDeStringParaData().parseData("09/03/2014"));
		when(calculavel.getFim()).thenReturn(new ParserDeStringParaData().parseData("11/03/2014"));
		when(calculavel.getValorDiaria()).thenReturn(null);
		
		CalculoDeValorPorPeriodoService calculoPorPeriodoService = new CalculoDeValorPorPeriodoService();
		Assert.assertEquals((Double)20.0, calculoPorPeriodoService.calcularValor(calculavel));
	}
	
}

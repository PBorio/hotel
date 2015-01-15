package domain.servicos;

import org.junit.Assert;
import org.junit.Test;

import domain.Reserva;
import domain.helpers.FakeReserva;

public class ModificadorDeValoresDaDiariaServiceTest {
	
	@Test
	public void seUmaReservaForDeApenasUmAdultoOValorDaReservaSera70PorCentoDoValorCalculado(){
		
		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(1);
		
		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		Assert.assertEquals((Double)70.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaDuasDeCriancasDe0a5NaBaixaTemporadaDeveAcrescentar40ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/06/2015").terminandoEm("02/06/2015").build();
		reserva.setNumeroAdultos(1);
		reserva.setNumeroCriancas0a5(4);

		Double valorDiaria = 400.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)480.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaDuasDeCriancasDe0a5NaBaixaTemporadaDeveAcrescentar50ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2015").terminandoEm("02/03/2015").build();
		reserva.setNumeroAdultos(1);
		reserva.setNumeroCriancas0a5(4);

		Double valorDiaria = 400.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)500.0, reserva.getValorDiaria());
	}

	@Test
	public void paraCadaAdultoAMaisNaAltaTemporadaHaUmAcrescimoDe100Reais(){
		Reserva reserva = new FakeReserva().iniciandoEm("01/01/2015").terminandoEm("02/01/2015").build();
		reserva.setNumeroAdultos(3);

		Double valorDiaria = 400.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)500.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaAdultoAMaisNaBaixaTemporadaHaUmAcrescimoDe80Reais(){
		Reserva reserva = new FakeReserva().iniciandoEm("01/06/2014").terminandoEm("02/06/2014").build();
		reserva.setNumeroAdultos(3);

		Double valorDiaria = 400.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)480.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaCriancaDe6a18NaBaixaTemporadaDeveAcrescentar70ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/06/2015").terminandoEm("02/06/2015").build();
		reserva.setNumeroAdultos(2);
		reserva.setNumeroCriancas6a16(1);
		reserva.setNumeroCriancas17a18(1);

		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)240.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaCriancaDe6a16NaAltaTemporadaDeveAcrescentar80ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2015").terminandoEm("02/03/2015").build();
		reserva.setNumeroAdultos(2);
		reserva.setNumeroCriancas6a16(1);

		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)180.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaCriancaDe17a18NaAltaTemporadaDeveAcrescentar80ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2015").terminandoEm("02/03/2015").build();
		reserva.setNumeroAdultos(2);
		reserva.setNumeroCriancas17a18(1);

		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)180.0, reserva.getValorDiaria());
	}

}

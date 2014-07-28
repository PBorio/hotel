package domain.servicos;

import org.junit.Assert;
import org.junit.Test;

import domain.Reserva;
import domain.helpers.FakeReserva;

public class ModificadorDeValoresDaDiariaServiceTest {
	
	@Test
	public void seUmaReservaForDeApenasUmAdultoOValorDaReservaSera60PorCentoDoValorCalculado(){

		
		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(1);
		
		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		Assert.assertEquals((Double)60.0, reserva.getValorDiaria());
	}
	
	@Test
	public void paraCadaParDeCriancasDe0a5DeveAcrescentar40ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(1);
		reserva.setNumeroCriancas0a5(4);

		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)180.0, reserva.getValorDiaria());
	}

	@Test
	public void paraCadaParDeCriancasDe6a16DeveAcrescentar70ReaisNoValorDaDiaria(){

		Reserva reserva = new FakeReserva().iniciandoEm("01/03/2014").terminandoEm("02/03/2014").build();
		reserva.setNumeroAdultos(2);
		reserva.setNumeroCriancas6a16(1);

		Double valorDiaria = 100.0;
		
		ModificadorDeValoresDaDiariaService modificador = new ModificadorDeValoresDaDiariaService();
		valorDiaria = modificador.aplicarModificadores(reserva, valorDiaria);
		reserva.setValorDiaria(valorDiaria);
		
		Assert.assertEquals((Double)170.0, reserva.getValorDiaria());
	}

}

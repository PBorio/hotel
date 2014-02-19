package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.Quarto;
import domain.Reserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class ServicoDeReservaTest {
	
	@Test
	public void seNenhumQuartoEstaDisponivelNoPeriodoDaReservaOServicoRetornaNulo(){
		
		Reserva reserva = criarReserva("01/03/2014", "06/03/2014");
		
		Quarto quarto = new Quarto();
		quarto.addReserva(criarReserva("01/02/2014", "15/03/2014"));
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertNull(servico.quartoDisponivelParaAReserva(reserva));
	}
	
	@Test
	public void casoHajaQuartoDisponivelNoPeriodoDaReservaOPrimeiroSeraIndicado(){

		Reserva reserva = criarReserva("01/03/2014", "06/03/2014"); 
		
		Quarto quarto = new Quarto();
		quarto.addReserva(criarReserva("15/03/2014", "31/03/2014"));
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertNotNull(servico.quartoDisponivelParaAReserva(reserva));
	}
	
	@Test
	public void seUmQuartoNaoTiverDisponibilidadeEUmSegundoTiverOServicoDeReservaRetornaOSegundo(){

		Reserva reserva = criarReserva("01/03/2014", "06/03/2014"); 
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.addReserva(criarReserva("01/03/2014", "15/03/2014"));
		
		Quarto outroQuarto = new Quarto();
		outroQuarto.setId(2l);
		outroQuarto.addReserva(criarReserva("16/03/2014", "31/03/2014"));
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		quartos.add(outroQuarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertEquals(outroQuarto, servico.quartoDisponivelParaAReserva(reserva));
	}

	private Reserva criarReserva(String inicio, String fim) {
		Reserva reservaJaExistente = new Reserva();
		ParserDeStringParaData parser = new ParserDeStringParaData();
		DateTime dataInicio = parser.parseData(inicio); 
		DateTime dataFim = parser.parseData(fim);
		reservaJaExistente = new Reserva();
		reservaJaExistente.setInicio(dataInicio);
		reservaJaExistente.setFim(dataFim);
		
		return reservaJaExistente;
	}
	

}

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
		Reserva outraReserva = criarReserva("01/02/2014", "15/03/2014");
		
		Quarto quarto = new Quarto();
		outraReserva.setQuarto(quarto);
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertEquals(0, servico.quartosDisponiveisParaAReserva(reserva).size());
	}
	
	@Test
	public void casoHajaQuartoDisponivelNoPeriodoDaReservaOPrimeiroSeraIndicado(){

		Reserva reserva = criarReserva("01/03/2014", "06/03/2014"); 
		Reserva outra = criarReserva("15/03/2014", "31/03/2014");
		
		Quarto quarto = new Quarto();
		outra.setQuarto(quarto);
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertTrue(servico.quartosDisponiveisParaAReserva(reserva).size() > 0);
	}
	
	@Test
	public void seUmQuartoNaoTiverDisponibilidadeEUmSegundoTiverOServicoDeReservaRetornaOSegundo(){

		Reserva reserva = criarReserva("01/03/2014", "06/03/2014");
		Reserva deUmAQuinzeDeMarco = criarReserva("01/03/2014", "15/03/2014");
		Reserva deDezesseisATreintaEUm = criarReserva("16/03/2014", "31/03/2014");
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		deUmAQuinzeDeMarco.setQuarto(quarto);
		
		Quarto outroQuarto = new Quarto();
		outroQuarto.setId(2l);
		deDezesseisATreintaEUm.setQuarto(outroQuarto);
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		quartos.add(outroQuarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertEquals(outroQuarto, servico.quartosDisponiveisParaAReserva(reserva).get(0));
	}
	
	@Test
	public void seACapacidadeDoQuartoForMenorQueONumeroDePessoasDaReservaEleNaoEstaraDisponivelParaAReserva(){

		Reserva reserva = criarReserva("01/03/2015", "06/03/2015");
		reserva.setNumeroAdultos(8);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCapacidade(5);
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertEquals(0,servico.quartosDisponiveisParaAReserva(reserva).size());
	}
	
	@Test
	public void umQuartoSoEstaDisponivelParaAReservaSeSuaCapacidadeForMaiorQueOuIgualaoONumeroDePessoasParaAReserva(){

		Reserva reserva = criarReserva("01/03/2015", "06/03/2015");
		reserva.setNumeroAdultos(5);
		
		Quarto quarto = new Quarto();
		quarto.setId(1l);
		quarto.setCapacidade(5);
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(quarto);
		
		ServicoDeReserva servico = new ServicoDeReserva(quartos);
		Assert.assertEquals(quarto, servico.quartosDisponiveisParaAReserva(reserva).get(0));
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

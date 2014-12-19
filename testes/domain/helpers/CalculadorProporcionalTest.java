package domain.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import domain.Pagamento;
import domain.Reserva;
import domain.servicos.helpers.CalculoDeProporcaoDePagamentoDasReservas;
import domain.servicos.tipos.TipoPagamento;

public class CalculadorProporcionalTest {
	
	@Test
	public void oCalculoDeProporcaoRecebeUmValorESuasPartesEDeterminaOValorDeCadaParteProporcionalmente(){
		Reserva primeiraReserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(500.0).build();
		Reserva segundaReserva =  new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(500.0).build();
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(primeiraReserva);
		reservas.add(segundaReserva);
		
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(500.0);
		CalculoDeProporcaoDePagamentoDasReservas calculo = new CalculoDeProporcaoDePagamentoDasReservas();
		calculo.dividirProporcionalmente(pagamento, reservas);
		Assert.assertEquals((Double)250.0, primeiraReserva.getValorPago());
		Assert.assertEquals((Double)250.0, segundaReserva.getValorPago());
	}
	
	@Test
	public void casoHajaRestoNaDivisaoProporcionalORestoIraComoPagamentoParaAReservaDeMaiorValor(){
		Reserva primeiraReserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(592.42).build();
		Reserva segundaReserva =  new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(499.1).build();
		Reserva terceiraReserva =  new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(451.88).build();
		//1543,4
		//38,384
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(primeiraReserva);
		reservas.add(segundaReserva);
		reservas.add(terceiraReserva);
		
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(688.90);
		CalculoDeProporcaoDePagamentoDasReservas calculo = new CalculoDeProporcaoDePagamentoDasReservas();
		calculo.dividirProporcionalmente(pagamento, reservas);
		
		Assert.assertEquals((Double)264.43, primeiraReserva.getValorPago());
		Assert.assertEquals((Double)222.77, segundaReserva.getValorPago());
		Assert.assertEquals((Double)201.7, terceiraReserva.getValorPago());
	}
	
	@Test
	public void casoHajaRestoNaDivisaoProporcionalORestoEOsValoresForemIguaisORestoIraParaAPrimeira(){
		Reserva primeiraReserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(500.0).build();
		Reserva segundaReserva =  new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(500.0).build();
		Reserva terceiraReserva =  new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("08/12/2014").terminandoEm("09/12/2014").comValorDaDiariaDe(500.0).build();
		//1543,4
		//38,384
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(primeiraReserva);
		reservas.add(segundaReserva);
		reservas.add(terceiraReserva);
		
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(1000.0);
		CalculoDeProporcaoDePagamentoDasReservas calculo = new CalculoDeProporcaoDePagamentoDasReservas();
		calculo.dividirProporcionalmente(pagamento, reservas);
		
		Assert.assertEquals((Double)333.34, primeiraReserva.getValorPago());
		Assert.assertEquals((Double)333.33, segundaReserva.getValorPago());
		Assert.assertEquals((Double)333.33, terceiraReserva.getValorPago());
	}
	
	private Pagamento criarPagamento(TipoPagamento tipo) {
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(tipo.getValue());
		pagamento.setDataPagamento(new Date());
		return pagamento;
	}

}

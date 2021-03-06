package domain;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;
import domain.servicos.tipos.TipoPagamento;

public class PagamentoReservaTest {

	@Test
	public void umaReservaPodeTerUmPagamento(){
		Pagamento pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertTrue(reserva.isPossuiPagamento());
	}
	
	@Test
	public void oSaldoDeUmaReservaEhOValorDaReservaMenosOValorJaPago(){
		Pagamento pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertEquals((Double)50.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void casoHajaDoisRegistrosDePagamentoOValorJaPagoEhASomaDosDois(){
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		Pagamento outro = criarPagamento(TipoPagamento.CARTAO);
		outro.setValor(30.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		reserva.addPagamento(outro);
		
		Assert.assertEquals((Double)20.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void oPagamentoPodeSerPorDepositoBancario(){
		Pagamento pagamento = criarPagamento(TipoPagamento.DEPOSITO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		Assert.assertEquals((Double)50.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void casoADataDoPagamentoNaoEstejaInformadaOValorDoPagamentoAindaNaoEhContabilizadoNoSaldoDaReserva(){
		Pagamento pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		pagamento.setDataPagamento(null);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		Assert.assertEquals((Double)100.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void paraOsCamposDataDepositoEValorDepositoDevemSerAtualizadosDataPagamentoEValor(){
		Pagamento pagamento = criarPagamento(TipoPagamento.DEPOSITO);
		
		Date hoje = new Date();
		pagamento.setValorDeposito(50.0);
		pagamento.setDataDeposito(hoje);
		pagamento.arrumaValores();
		
		Assert.assertEquals((Double)50.0, pagamento.getValor());
		Assert.assertEquals(hoje, pagamento.getDataPagamento());
	}
	
	@Test
	public void umPagamentoPodeSerFeitaParaDuasReservas(){
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(500.0);
		
		
		Reserva reserva1 = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(500).build();
		Reserva reserva2 = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(500).build();
		
		AgrupadorReservas agrupador = new AgrupadorReservas();
		agrupador.addReserva(reserva1);
		agrupador.addReserva(reserva2);
		
		agrupador.addPagamento(pagamento);
		
		Assert.assertTrue(reserva1.isPossuiPagamento());
		Assert.assertTrue(reserva2.isPossuiPagamento());
	}
	
	@Test
	public void umPagamentoParaReservasDiferentesDeveSerDivididoProporcionalmente(){
		Pagamento pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(500.0);
		
		
		Reserva reserva1 = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(500).build();
		Reserva reserva2 = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(500).build();
		
		AgrupadorReservas agrupador = new AgrupadorReservas();
		agrupador.addReserva(reserva1);
		agrupador.addReserva(reserva2);
		
		agrupador.addPagamento(pagamento);
		
		Assert.assertEquals((Double)250.0, reserva1.getValorPago());
		Assert.assertEquals((Double)250.0, reserva2.getValorPago());
	}
	
	private Pagamento criarPagamento(TipoPagamento tipo) {
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(tipo.getValue());
		pagamento.setDataPagamento(new Date());
		return pagamento;
	}
	
}

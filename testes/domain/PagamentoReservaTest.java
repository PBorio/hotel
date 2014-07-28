package domain;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;
import domain.servicos.tipos.TipoPagamento;

public class PagamentoReservaTest {

	@Test
	public void umaReservaPodeTerUmPagamento(){
		PagamentoReserva pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertTrue(reserva.isPossuiPagamento());
	}
	
	@Test
	public void oSaldoDeUmaReservaEhOValorDaReservaMenosOValorJaPago(){
		PagamentoReserva pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertEquals((Double)50.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void casoHajaDoisRegistrosDePagamentoOValorJaPagoEhASomaDosDois(){
		PagamentoReserva pagamento =criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		
		PagamentoReserva outro = criarPagamento(TipoPagamento.CARTAO);
		outro.setValor(30.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		reserva.addPagamento(outro);
		
		Assert.assertEquals((Double)20.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void oPagamentoPodeSerPorDepositoBancario(){
		PagamentoReserva pagamento = criarPagamento(TipoPagamento.DEPOSITO);
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		Assert.assertEquals((Double)50.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void casoADataDoPagamentoNaoEstejaInformadaOValorDoPagamentoAindaNaoEhContabilizadoNoSaldoDaReserva(){
		PagamentoReserva pagamento = criarPagamento(TipoPagamento.CARTAO);
		pagamento.setValor(50.0);
		pagamento.setDataPagamento(null);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		Assert.assertEquals((Double)100.0, reserva.getSaldoAPagar());
	}
	
	private PagamentoReserva criarPagamento(TipoPagamento tipo) {
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setTipoPagamento(tipo.getValue());
		pagamento.setDataPagamento(new Date());
		return pagamento;
	}
	
}

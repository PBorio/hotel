package domain;


import org.junit.Assert;
import org.junit.Test;

import domain.helpers.FakeReserva;

public class ReservaParaPagamentoTest {

	@Test
	public void umaReservaPodeTerUmPagamento(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertTrue(reserva.isPossuiPagamento());
	}
	
	@Test
	public void oSaldoDeUmaReservaEhOValorDaReservaMenosOValorJaPago(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setValor(50.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		
		Assert.assertEquals((Double)50.0, reserva.getSaldoAPagar());
	}
	
	@Test
	public void casoHajaDoisRegistrosDePagamentoOValorJaPagoEhASomaDosDois(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setValor(50.0);
		
		PagamentoReserva outro = new PagamentoReserva();
		outro.setValor(30.0);
		
		Reserva reserva = new FakeReserva().comNumeroDeAdultos(2).iniciandoEm("18/07/2014").terminandoEm("19/07/2014").comValorDaDiariaDe(100).build();
		reserva.addPagamento(pagamento);
		reserva.addPagamento(outro);
		
		
		Assert.assertEquals((Double)20.0, reserva.getSaldoAPagar());
	}
	
}

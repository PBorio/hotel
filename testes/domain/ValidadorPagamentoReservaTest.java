package domain;

import java.util.Date;

import org.junit.Test;

import domain.exceptions.PagamentoInvalidoException;
import domain.helpers.ValidadorPagamentoReserva;
import domain.servicos.tipos.TipoPagamento;

public class ValidadorPagamentoReservaTest {
	
	@Test(expected=PagamentoInvalidoException.class)
	public void umPagamentoNaoEhValidoSeOTipoDePagamentoNaoFoiInformado(){
		Pagamento pagamento = criarPagamento();
		pagamento.setTipoPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoOValorNaoPodeSerMenorOuIgualAZero(){
		Pagamento pagamento = criarPagamento();
		pagamento.setValor(0.0);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoOValorNaoPodeSerNula(){
		Pagamento pagamento = criarPagamento();
		pagamento.setValor(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoONumeroDoCartaoTemQuerSerInformado(){
		Pagamento pagamento = criarPagamento();
		pagamento.setNumeroCartao("");
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoADataDePagamentoTemQuerSerInformado(){
		Pagamento pagamento = criarPagamento();
		pagamento.setDataPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoViaDepositoOBancoTemQueSerInformado(){
		Pagamento pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoViaDepositoPrecisaTerADataDeDepositoOuDePagamentoInformada(){
		Pagamento pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setDataPrevisao(null);
		pagamento.setDataPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void casoADataDePagamentoTenhaSidoInformadaOValorEhObrigatorio(){
		Pagamento pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoDeCartaoEhValidoSeTiverDataDePagamentoNumeroDoCartaoEValor(){
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(10.0);
		pagamento.setNumeroCartao("1234 5678");
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoPorDepositoPodeTerSomenteADataDePrevisaoEOBanco(){
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco("Itau");
		pagamento.setDataPrevisao(new Date());
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoPorDepositoQueTenhaDataDePagamentoDeveTerTambemBancoEValor(){
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco("Itau");
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(50.0);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoEmDinheiroEhValidoSeTiverDataEValor(){
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.DINHEIRO.getValue());
		pagamento.setBanco(null);
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(50.0);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}

	private Pagamento criarPagamento() {
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setValor(50.0);
		pagamento.setNumeroCartao("1234 5678 XXXX XXXX");
		pagamento.setDataDeposito(new Date());
		pagamento.setDataPrevisao(new Date());
		pagamento.setBanco("Bradesco");
		return pagamento;
	}

}

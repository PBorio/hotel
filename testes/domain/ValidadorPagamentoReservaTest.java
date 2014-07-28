package domain;

import java.util.Date;

import org.junit.Test;

import domain.exceptions.PagamentoInvalidoException;
import domain.helpers.ValidadorPagamentoReserva;
import domain.servicos.tipos.TipoPagamento;

public class ValidadorPagamentoReservaTest {
	
	@Test(expected=PagamentoInvalidoException.class)
	public void umPagamentoNaoEhValidoSeOTipoDePagamentoNaoFoiInformado(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setTipoPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoOValorNaoPodeSerMenorOuIgualAZero(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setValor(0.0);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoOValorNaoPodeSerNula(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setValor(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoONumeroDoCartaoTemQuerSerInformado(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setNumeroCartao("");
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoComCartaoADataDePagamentoTemQuerSerInformado(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setDataPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoViaDepositoOBancoTemQueSerInformado(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void paraPagamentoViaDepositoPrecisaTerADataDeDepositoOuDePagamentoInformada(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setDataPrevisao(null);
		pagamento.setDataPagamento(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test(expected=PagamentoInvalidoException.class)
	public void casoADataDePagamentoTenhaSidoInformadaOValorEhObrigatorio(){
		PagamentoReserva pagamento = criarPagamento();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(null);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoDeCartaoEhValidoSeTiverDataDePagamentoNumeroDoCartaoEValor(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(10.0);
		pagamento.setNumeroCartao("1234 5678");
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoPorDepositoPodeTerSomenteADataDePrevisaoEOBanco(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco("Itau");
		pagamento.setDataPrevisao(new Date());
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}
	
	@Test
	public void umPagamentoPorDepositoQueTenhaDataDePagamentoDeveTerTambemBancoEValor(){
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setTipoPagamento(TipoPagamento.DEPOSITO.getValue());
		pagamento.setBanco("Itau");
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(50.0);
		ValidadorPagamentoReserva validadorPagamentoReserva = new ValidadorPagamentoReserva(pagamento);
		validadorPagamentoReserva.validar();
	}

	private PagamentoReserva criarPagamento() {
		PagamentoReserva pagamento = new PagamentoReserva();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setValor(50.0);
		pagamento.setNumeroCartao("1234 5678 XXXX XXXX");
		pagamento.setDataDeposito(new Date());
		pagamento.setDataPrevisao(new Date());
		pagamento.setBanco("Bradesco");
		return pagamento;
	}

}

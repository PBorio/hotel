package domain.helpers;

import domain.Pagamento;
import domain.exceptions.PagamentoInvalidoException;
import domain.servicos.tipos.TipoPagamento;

public class ValidadorPagamentoReserva {

	private Pagamento pagamento;

	public ValidadorPagamentoReserva(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void validar() {
		
		if (pagamento.getTipoPagamento() == null)
			throw new PagamentoInvalidoException("Tipo de Pagamento não foi informado");
		
		if (TipoPagamento.CARTAO.getValue().equals(pagamento.getTipoPagamento()))
			validarCartao();

		if (TipoPagamento.DEPOSITO.getValue().equals(pagamento.getTipoPagamento()))
			validarDeposito();
		
	}

	private void validarDeposito() {
		if (pagamento.getBanco() == null || "".equals(pagamento.getBanco().trim()))
			throw new PagamentoInvalidoException("Banco não informado no pagamento.");
		
		if (pagamento.getDataPrevisao() == null && pagamento.getDataPagamento() == null)
			throw new PagamentoInvalidoException("Nem a data de previsão nem pagamento foi informada.");
		
		if (pagamento.getDataPagamento() != null){
			if (pagamento.getValor() == null || pagamento.getValor().doubleValue() == 0.0)
				throw new PagamentoInvalidoException("O valor do pagamento não foi inforamdo.");
		}
	}

	private void validarCartao() {
		if (pagamento.getValor() == null || pagamento.getValor().doubleValue() == 0.0)
			throw new PagamentoInvalidoException("Valor do pagamento deve ser informado.");
		
		if (pagamento.getNumeroCartao() == null || "".equals(pagamento.getNumeroCartao().trim()))
			throw new PagamentoInvalidoException("Número do cartão não foi informado.");
		
		if (pagamento.getDataPagamento() == null)
			throw new PagamentoInvalidoException("Data do pagamento não informada.");
	}

}

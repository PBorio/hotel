package domain.servicos.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import domain.Pagamento;
import domain.PagamentoReserva;
import domain.Reserva;

public class CalculoDeProporcaoDePagamentoDasReservas {

	public void dividirProporcionalmente(Pagamento pagamento, List<Reserva> reservas) {
		Double valorDasReservas = 0.0;
		for (Reserva r : reservas){
			valorDasReservas += r.getValorReserva();
		}
		
		BigDecimal bd = new BigDecimal(valorDasReservas.toString());
	  	valorDasReservas = bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	  	
	  	Double somaDosPagamentos = 0.0;
	  	for (Reserva r : reservas){
	  		Double percentual = (r.getValorReserva() / valorDasReservas)*100;
	  		
	  		BigDecimal bigdecimal = new BigDecimal(percentual.toString());
		  	percentual = bigdecimal.setScale(4, RoundingMode.HALF_EVEN).doubleValue();
		  	
		  	Double valorPagamentoDestaReserva = pagamento.getValor() * (percentual/100);
		  	
		  	bigdecimal = new BigDecimal(valorPagamentoDestaReserva.toString());
		  	valorPagamentoDestaReserva = bigdecimal.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		  	
		  	somaDosPagamentos += valorPagamentoDestaReserva;
		  	
		  	PagamentoReserva pr = new PagamentoReserva(r, pagamento);
		  	pr.setValor(valorPagamentoDestaReserva);
		  	r.addPagamentoReserva(pr);
		  	pagamento.addPagamentoReserva(pr);
	  	}
	  	
	  	bd = new BigDecimal(somaDosPagamentos.toString());
	  	somaDosPagamentos = bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	  	
	  	Double diferenca = pagamento.getValorPagamento() - somaDosPagamentos;
	  	bd = new BigDecimal(diferenca.toString());
	  	diferenca = bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();

	  	if (diferenca.doubleValue() > 0.0){
	  		Reserva primeiraReserva = reservas.get(0);
	  		PagamentoReserva pagamentoPrimeiraReserva = new PagamentoReserva(primeiraReserva, pagamento);
	  		pagamentoPrimeiraReserva.setValor(diferenca);
	  		primeiraReserva.addPagamentoReserva(pagamentoPrimeiraReserva);
	  	}
		
	}

}

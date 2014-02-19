package domain.servicos;

import java.util.List;

import domain.Categoria;
import domain.PoliticaDePrecos;
import domain.Reserva;

public class ServicoDeCalculoDePrecos {

	private List<PoliticaDePrecos> politicas;

	public ServicoDeCalculoDePrecos(List<PoliticaDePrecos> politicas) {
		this.politicas = politicas;
	}

	public void calcularEInformarValorNaReserva(Reserva reserva) {
		
		Double valorDaDiaria = 0.0;
		for (PoliticaDePrecos politica : this.politicas){
			if (!politica.getCategoria().equals(reserva.getQuarto().getCategoria()))
				continue;
			
			Reserva periodoDaPoliticaDePrecos = new Reserva();
			periodoDaPoliticaDePrecos.setInicio(politica.getInicio());
			periodoDaPoliticaDePrecos.setFim(politica.getFim());
			
			if (reserva.coincideCom(periodoDaPoliticaDePrecos))
				valorDaDiaria = politica.getValorDiaria();
			
		}
		
		if (naoEncontrouPoliticaParaAReserva(valorDaDiaria)){
			PoliticaDePrecos politicaPadrao = getPoliticaPadraoParaACategoria(reserva.getQuarto().getCategoria());
			valorDaDiaria = politicaPadrao.getValorDiaria();
		}
		
		if (naoEncontrouPoliticaParaAReserva(valorDaDiaria))
			throw new RuntimeException("Não há política de preços para a categoria: "+reserva.getQuarto().getCategoria().getDescricao());
		
		reserva.setValorDiaria(valorDaDiaria);
	}

	private PoliticaDePrecos getPoliticaPadraoParaACategoria(Categoria categoria) {
		for (PoliticaDePrecos politica : this.politicas){
			if (!politica.isPadrao())
				continue;
			
			if (politica.getCategoria().equals(categoria))
				return politica;
		}
		return null;
	}

	private boolean naoEncontrouPoliticaParaAReserva(Double valorDaReserva) {
		return valorDaReserva.doubleValue() == 0.0;
	}

}

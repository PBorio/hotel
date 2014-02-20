package domain.servicos;

import java.util.List;

import org.joda.time.DateTime;

import domain.Categoria;
import domain.PoliticaDePrecos;
import domain.Reserva;
import domain.exceptions.HotelException;

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
			periodoDaPoliticaDePrecos.setInicio(new DateTime(politica.getInicio().getTime()));
			periodoDaPoliticaDePrecos.setFim(new DateTime(politica.getFim()));
			
			if (reserva.coincideCom(periodoDaPoliticaDePrecos))
				valorDaDiaria = politica.getValorDiaria();
			
		}
		
		if (naoEncontrouPoliticaParaAReserva(valorDaDiaria)){
			PoliticaDePrecos politicaPadrao = getPoliticaPadraoParaACategoria(reserva.getQuarto().getCategoria());
			
			if (politicaPadrao == null)
				throw new HotelException("Não há política de preços para a categoria: "+reserva.getQuarto().getCategoria().getDescricao());
			
			valorDaDiaria = politicaPadrao.getValorDiaria();
		}
		
		if (naoEncontrouPoliticaParaAReserva(valorDaDiaria))
			throw new HotelException("Não há política de preços para a categoria: "+reserva.getQuarto().getCategoria().getDescricao());
		
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

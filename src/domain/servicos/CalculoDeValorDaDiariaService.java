package domain.servicos;

import java.util.List;

import org.joda.time.DateTime;

import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.servicos.helpers.Periodo;

public class CalculoDeValorDaDiariaService {

	private List<PoliticaDePrecos> politicas;

	public CalculoDeValorDaDiariaService(List<PoliticaDePrecos> politicas) {
		this.politicas = politicas;
	}
	
	public Double calcularValorDaDiaria(Periodo periodo, Quarto quarto) {
		Double valorDaDiaria = 0.0;
		
			for (PoliticaDePrecos politica : this.politicas){
				if (!politica.getCategoria().equals(quarto.getCategoria()))
					continue;
				
				Periodo outroPeriodo = new Periodo(new DateTime(politica.getInicio()), new DateTime(politica.getFim()));
				if (periodo.coincideCom(outroPeriodo))
					valorDaDiaria = politica.getValorDiaria();
				
			}
			
			if (naoEncontrouPoliticaParaAReserva(valorDaDiaria)){
				if (quarto.getCategoria() == null || quarto.getCategoria().getValor() == null)
					throw new HotelException("Valor para a categoria do quarto: "+quarto.getCategoria().getDescricao());
				
				valorDaDiaria = quarto.getCategoria().getValor();
			}
			
			if (naoEncontrouPoliticaParaAReserva(valorDaDiaria))
				throw new HotelException("Não há política de preços para a categoria: "+quarto.getCategoria().getDescricao());
			
			return valorDaDiaria;
	}


	public void calcularEInformarValorNaReserva(Reserva reserva) {
		Periodo periodo = new Periodo(reserva.getInicio(), reserva.getFim());
		Double valorDaDiaria = this.calcularValorDaDiaria(periodo, reserva.getQuarto());
		reserva.setValorDiaria(valorDaDiaria);
		valorDaDiaria = aplicarModificadoresDeValor(reserva, valorDaDiaria);
		reserva.setValorDiaria(valorDaDiaria);
	}

	private Double aplicarModificadoresDeValor(Reserva reserva, Double valorDaDiaria) {
		return new ModificadorDeValoresDaDiariaService().aplicarModificadores(reserva, valorDaDiaria);
	}

	private boolean naoEncontrouPoliticaParaAReserva(Double valorDaReserva) {
		return valorDaReserva.doubleValue() == 0.0;
	}


}

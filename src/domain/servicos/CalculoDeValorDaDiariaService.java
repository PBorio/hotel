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
	
	public Double calcularValorDaDiaria(Reserva reserva, Quarto quarto) {
		Double valorDaDiaria = 0.0;
			Periodo periodo = new Periodo(reserva.getInicio(), reserva.getFim());

			for (PoliticaDePrecos politica : this.politicas){
				if (!politica.getCategoria().equals(quarto.getCategoria()))
					continue;
				
				Periodo outroPeriodo = new Periodo(new DateTime(politica.getInicio()), new DateTime(politica.getFim()));
				if (periodo.coincideCom(outroPeriodo))
					valorDaDiaria = politica.valorParaAReserva(reserva);
				
			}
			
			if (naoEncontrouPoliticaParaAReserva(valorDaDiaria)){
				if (quarto.getCategoria() == null || quarto.getCategoria().getValor() == null)
					throw new HotelException("Valor para a categoria do quarto: "+quarto.getCategoria().getDescricao());
				
				valorDaDiaria = quarto.getCategoria().valorParaAReserva(reserva);
			}
			
			if (naoEncontrouPoliticaParaAReserva(valorDaDiaria))
				throw new HotelException("Não há política de preços para a categoria: "+quarto.getCategoria().getDescricao());
			
			return valorDaDiaria;
	}


	public void calcularEInformarValorNaReserva(Reserva reserva) {
		Double valorDaDiaria = this.calcularValorDaDiaria(reserva, reserva.getQuarto());
		reserva.setValorDiaria(valorDaDiaria);
	}

	private boolean naoEncontrouPoliticaParaAReserva(Double valorDaReserva) {
		return valorDaReserva.doubleValue() == 0.0;
	}


}

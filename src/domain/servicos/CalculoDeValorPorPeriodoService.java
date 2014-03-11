package domain.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.time.DateTime;
import org.joda.time.Days;

import domain.exceptions.ValorDiariaNaoInformadoException;
import domain.interfaces.CalculavelPorPeriodo;

public class CalculoDeValorPorPeriodoService {

	public Double calcularValor(CalculavelPorPeriodo calculavel) {
		Integer numeroDeDias = numeroDeDiasEntreInicioEFim(calculavel.getInicio(), calculavel.getFim());
		return calcularOValor(calculavel, numeroDeDias);
	}
	
	public Double calcularValorAteAData(CalculavelPorPeriodo calculavel, DateTime data) {
		Integer numeroDeDias = numeroDeDiasEntreInicioEFim(calculavel.getInicio(), data);
		return calcularOValor(calculavel, numeroDeDias);
	}


	private Double calcularOValor(CalculavelPorPeriodo calculavel, Integer numeroDeDias) {
		
		valorDiariaNaoPodeSerNulo(calculavel.getValorDiaria());
		
		Double valor = ((double)numeroDeDias * calculavel.getValorDiaria());
		if (valor.equals(new Double(0.0)))
	  		return valor;
		
	  	BigDecimal bd = new BigDecimal(valor.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	private void valorDiariaNaoPodeSerNulo(Double valorDiaria) {
		if (valorDiaria == null)
			throw new ValorDiariaNaoInformadoException("O valor da diária não foi informado.");
	}

	private Integer numeroDeDiasEntreInicioEFim(DateTime inicio, DateTime fim) {
		Integer numeroDeDias =  Days.daysBetween(inicio, fim).getDays();
		return numeroDeDias;
	}

	
	

}

package domain.servicos;

import org.joda.time.DateTime;

import domain.Reserva;
import domain.servicos.helpers.Periodo;

public class ModificadorDeValoresDaDiariaService {

	public Double aplicarModificadores(Reserva reserva, Double valorDaDiaria) {
		if (reserva.isSoParaUmAdulto())
			valorDaDiaria *= 0.7;
		
		valorDaDiaria += valorParaAdultosExtra(reserva);
		
		valorDaDiaria += valorParaCriancasDe0a5(reserva);
		
		valorDaDiaria += valorParaCriancasDe6a16(reserva);
		
		return valorDaDiaria;
	}

	private Double valorParaCriancasDe6a16(Reserva reserva) {
		Double valorAcrescimo = 70.0;
		Double valorAAcrescer = 0.0;
		
		if (isAltaTemporada(reserva))
			valorAcrescimo = 80.0;
		
		if (reserva.getNumeroCriancas6a16() != null && reserva.getNumeroCriancas6a16().intValue() > 0)
			valorAAcrescer += (valorAcrescimo * reserva.getNumeroCriancas6a16().intValue());
		
		if (reserva.getNumeroCriancas17a18() != null && reserva.getNumeroCriancas17a18().intValue() > 0)
			valorAAcrescer += (valorAcrescimo * reserva.getNumeroCriancas17a18().intValue());
		
		return valorAAcrescer;
	}

	private Double valorParaCriancasDe0a5(Reserva reserva) {
		Double valorDoAcrescimo = 40.0;
		if (isAltaTemporada(reserva))
			valorDoAcrescimo = 50.0;
		return (valorDoAcrescimo * reserva.getParDeCriancasDe0a5());
	}

	private Double valorParaAdultosExtra(Reserva reserva) {
		
		if (isAltaTemporada(reserva)){
			return valorAdultosExtraAltaTemporada(reserva);
		}else{
			return valorAdultosExtraBaixaTemporada(reserva);
		}
	}

	private Double valorAdultosExtraBaixaTemporada(Reserva reserva) {
Integer numeroDeAdultoExtra = reserva.getNumeroAdultos() - 2;
		
		if (numeroDeAdultoExtra > 0)
			return (numeroDeAdultoExtra * 80.0);
		return 0.0;
	}

	private Double valorAdultosExtraAltaTemporada(Reserva reserva) {
		Integer numeroDeAdultoExtra = reserva.getNumeroAdultos() - 2;
		
		if (numeroDeAdultoExtra > 0)
			return (numeroDeAdultoExtra * 100.0);
		return 0.0;
	}

	private boolean isAltaTemporada(Reserva reserva) {
		Periodo altaTemporada = new Periodo(new DateTime(2014,12,21,0,0,0,0), new DateTime(2015,3,20,0,0,0,0));
		Periodo periodoResreva = new Periodo(reserva.getInicio(), reserva.getFim());
		if (periodoResreva.coincideCom(altaTemporada))
			return true;
		
		return false;
	}

}

package domain.servicos;

import domain.Reserva;

public class ModificadorDeValoresDaDiariaService {

	public Double aplicarModificadores(Reserva reserva, Double valorDaDiaria) {
		if (reserva.isSoParaUmAdulto())
			valorDaDiaria *= 0.6;
		
		valorDaDiaria += (40.0 * reserva.getParDeCriancasDe0a5());
		
		if (reserva.getNumeroCriancas6a16() != null && reserva.getNumeroCriancas6a16().intValue() > 0)
			valorDaDiaria += (70.0 * reserva.getNumeroCriancas6a16().intValue());
		
		return valorDaDiaria;
	}

}

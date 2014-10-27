package domain.servicos;

import java.util.List;

import org.joda.time.DateTime;

import domain.PoliticaDePrecos;
import domain.exceptions.PoliticaComDataCoincidenteException;
import domain.servicos.helpers.Periodo;

public class PoliticaDePrecoService {

	private List<PoliticaDePrecos> politicasExistentes;

	public PoliticaDePrecoService(List<PoliticaDePrecos> politicasExistentes) {
		this.politicasExistentes = politicasExistentes;
	}

	public boolean validar(PoliticaDePrecos politica) {
		for (PoliticaDePrecos p : politicasExistentes){
			if (p.equals(politica))
				continue;
			
			if (!p.getCategoria().equals(politica.getCategoria()))
				continue;
			
			Periodo periodoPoliticaExsistente = new Periodo(new DateTime(p.getInicio()), new DateTime(p.getFim()));
			Periodo periodoNovaPolitica = new Periodo(new DateTime(politica.getInicio()), new DateTime(politica.getFim()));
			
			if (periodoPoliticaExsistente.coincideCom(periodoNovaPolitica))
				throw new PoliticaComDataCoincidenteException("Já existe uma política para esta categoria nesta data.");
		}
		return true;
	}

}

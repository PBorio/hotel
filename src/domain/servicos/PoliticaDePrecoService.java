package domain.servicos;

import java.util.List;

import org.joda.time.DateTime;

import domain.PoliticaDePrecos;
import domain.exceptions.PoliticaComDataCoincidenteException;
import domain.exceptions.PoliticaPadraoJaExistenteException;
import domain.servicos.helpers.Periodo;

public class PoliticaDePrecoService {

	private List<PoliticaDePrecos> politicasExistentes;

	public PoliticaDePrecoService(List<PoliticaDePrecos> politicasExistentes) {
		this.politicasExistentes = politicasExistentes;
	}

	public boolean validar(PoliticaDePrecos politica) {
		for (PoliticaDePrecos p : politicasExistentes){
			if (!p.getCategoria().equals(politica.getCategoria()))
				continue;
			
			if (politica.isPadrao()){
				if (p.isPadrao())
					throw new PoliticaPadraoJaExistenteException("J� existe pol�tica padr�o para esta categoria.");
			}else{
				if (p.isPadrao())
					continue;
				
				Periodo periodoPoliticaExsistente = new Periodo(new DateTime(p.getInicio()), new DateTime(p.getFim()));
				Periodo periodoNovaPolitica = new Periodo(new DateTime(p.getInicio()), new DateTime(p.getFim()));
				
				if (periodoPoliticaExsistente.coincideCom(periodoNovaPolitica))
					throw new PoliticaComDataCoincidenteException("J� existe uma pol�tica para esta categoria nesta data.");
			}
		}
		return true;
	}

}

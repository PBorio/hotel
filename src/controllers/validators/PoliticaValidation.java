package controllers.validators;

import java.util.List;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.PoliticaDePrecos;
import domain.exceptions.HotelException;
import domain.servicos.PoliticaDePrecoService;

public class PoliticaValidation {

	private Validator validator;
	private List<PoliticaDePrecos> politicasExistentes;

	public PoliticaValidation(Validator validator,
			List<PoliticaDePrecos> politicasExistentes) {
				this.validator = validator;
				this.politicasExistentes = politicasExistentes;
	}
	
	public Validator validar(PoliticaDePrecos politicaDePrecos){
		if (politicaDePrecos.getDescricao() == null || politicaDePrecos.getDescricao().trim().equals(""))
			validator.add(new ValidationMessage("Descri��o da pol�tica � obrigat�ria", "pol�tica"));
		
		if (politicaDePrecos.getCategoria() == null || politicaDePrecos.getCategoria().getId().longValue() == -1){
			validator.add(new ValidationMessage("Categoria da pol�tica � obrigat�ria", "pol�tica"));
		}
		
		if ((politicaDePrecos.getInicio() == null || politicaDePrecos.getFim() == null)){
			if (politicaDePrecos.getInicio() == null)
				validator.add(new ValidationMessage("Data de in�cio � obrigat�ria", "inicio"));
			
			if (politicaDePrecos.getFim() == null)
				validator.add(new ValidationMessage("Data de fim � obrigat�ria", "fim"));
		}
		else{
			if (politicaDePrecos.getFim().before(politicaDePrecos.getInicio()))
				validator.add(new ValidationMessage("Data de in�cio anterior a data de fim", "datas"));
		}
		try{
			PoliticaDePrecoService politicaService = new PoliticaDePrecoService(politicasExistentes);
			politicaService.validar(politicaDePrecos);
		}catch(HotelException e){
			validator.add(new ValidationMessage(e.getMessage(), "politica"));
		}
		return validator;
	}

}

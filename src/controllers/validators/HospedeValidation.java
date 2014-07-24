package controllers.validators;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.Hospede;

public class HospedeValidation {
	
	private Validator validator;

	public HospedeValidation(Validator validator) {
		this.validator = validator;
	}
	
	public Validator validarHospede(Hospede hospede) {
		
		if (hospede.getNome() == null || hospede.getNome().trim().equals(""))
			validator.add(new ValidationMessage("Nome é obrigatorio", "hospede"));
		
		if (hospede.getEmail() == null || hospede.getEmail().trim().equals("")){
			validator.add(new ValidationMessage("Email do Hospede é obrigatorio", "email"));
		}else{
			if (!hospede.getEmail().contains("@"))
				validator.add(new ValidationMessage("Email inválido", "email"));
		}
		
		if (hospede.getTelefone() == null || hospede.getTelefone().trim().equals(""))
			validator.add(new ValidationMessage("Telefone do Hospede é obrigatorio", "email"));
		
		return this.validator;
	}

}

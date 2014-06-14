package controllers.validators;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.views.reservas.ReservasView;

public class ReservaValidation {
	
	private Validator validator;
	private ReservasView reservaView;

	public ReservaValidation(Validator validator,ReservasView reservasView) {
		this.validator = validator;
		this.reservaView = reservasView;
	}
	
	public Validator criarValidacoes(){
		
		if (reservaView.getQuartos().isEmpty())
			validator.add(new ValidationMessage("Quarto � obrigat�rio", "quarto"));
		
		if (reservaView.getHospedeResponsavel() == null)
			validator.add(new ValidationMessage("Nome � obrigatorio", "hospede"));
		
		if (reservaView.getChegada() == null)
			validator.add(new ValidationMessage("Data de Chegada � obrigat�ria", "inicio"));
		
		if (reservaView.getSaida() == null)
			validator.add(new ValidationMessage("Data de Sa�da � obrigat�ria", "fim"));
		
		if (reservaView.getNumeroAdultos() == null)
			validator.add(new ValidationMessage("Numero de Adultos � obrigat�rio", "numeroAdultos"));
		
		if (reservaView.getEmailHospede() == null || reservaView.getEmailHospede().trim().equals("")){
			validator.add(new ValidationMessage("Email do Hospede � obrigatorio", "email"));
		}else{
			if (!reservaView.getEmailHospede().contains("@"))
				validator.add(new ValidationMessage("Email inv�lido", "email"));
		}
		
		if (reservaView.getTelefoneHospede() == null || reservaView.getTelefoneHospede().trim().equals(""))
			validator.add(new ValidationMessage("Telefone do Hospede � obrigatorio", "email"));
		
		return this.validator;
	}

}

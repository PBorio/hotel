package controllers.validators;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.views.reservas.DetalhesDosParametros;
import controllers.views.reservas.ReservasView;
import domain.Hospede;

public class ReservaValidation {
	
	private Validator validator;
	private ReservasView reservaView;

	public ReservaValidation(Validator validator,ReservasView reservasView) {
		this.validator = validator;
		this.reservaView = reservasView;
	}
	
	public Validator criarValidacoes(){
		
		if (reservaView.getQuartos().isEmpty())
			validator.add(new ValidationMessage("Quarto é obrigatório", "quarto"));
		
		if (reservaView.getHospedeResponsavel() == null)
			validator.add(new ValidationMessage("Nome é obrigatorio", "hospede"));
		
		if (reservaView.getChegada() == null)
			validator.add(new ValidationMessage("Data de Chegada é obrigatória", "inicio"));
		
		if (reservaView.getSaida() == null)
			validator.add(new ValidationMessage("Data de Saída é obrigatória", "fim"));
		
		for (DetalhesDosParametros detalhe : reservaView.getParametrosReserva().getDetalhes()){
			if (detalhe.getNumeroAdultos() == null)
				validator.add(new ValidationMessage("Numero de Adultos é obrigatório", "numeroAdultos"));
		}
		
		if (reservaView.getEmailHospede() == null || reservaView.getEmailHospede().trim().equals("")){
			validator.add(new ValidationMessage("Email do Hospede é obrigatorio", "email"));
		}else{
			if (!reservaView.getEmailHospede().contains("@"))
				validator.add(new ValidationMessage("Email inválido", "email"));
		}
		
		if (reservaView.getTelefoneHospede() == null || reservaView.getTelefoneHospede().trim().equals(""))
			validator.add(new ValidationMessage("Telefone do Hospede é obrigatorio", "email"));
		
		return this.validator;
	}

	public Validator validarBusca() {
		
		if (reservaView.getChegada() == null || reservaView.getSaida() == null){
			if (reservaView.getChegada() == null)
				validator.add(new ValidationMessage("Data de Chegada é obrigatória", "inicio"));
			
			if (reservaView.getSaida() == null)
				validator.add(new ValidationMessage("Data de Saída é obrigatória", "fim"));
		}
		else{
			
			DateTime chegada = new DateTime(reservaView.getChegada().getTime()).withTimeAtStartOfDay();
			DateTime hoje = new DateTime().withTimeAtStartOfDay();
			if (chegada.isBefore(hoje))
				validator.add(new ValidationMessage("Data de chegada anterior a data de hoje", "datas"));
			
			if (reservaView.getSaida().before(reservaView.getChegada()))
				validator.add(new ValidationMessage("Data de saída anterior a data de chegada", "datas"));
		}

		for (DetalhesDosParametros detalhe : reservaView.getParametrosReserva().getDetalhes()){
			if (detalhe.getNumeroAdultos() == null)
				validator.add(new ValidationMessage("Numero de Adultos é obrigatório", "numeroAdultos"));
		}
		return this.validator;
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

package domain.helpers;

import org.joda.time.DateTime;

import domain.Hospede;
import domain.Quarto;
import domain.Reserva;
import domain.servicos.helpers.ParserDeStringParaData;

public class FakeReserva {
	
	private Reserva reserva = new Reserva();
	
	public FakeReserva iniciandoEm(String data){
		DateTime inicio = parseData(data);
		this.reserva.setInicio(inicio);
		return this;
	}

	public FakeReserva terminandoEm(String data){
		DateTime fim = parseData(data);
		this.reserva.setFim(fim);
		return this;
	}
	
	public Reserva build(){
		return reserva;
	}
	
	public FakeReserva paraOHospede(String nome) {
		Hospede hospede = new Hospede();
		hospede.setNome(nome);
		reserva.setHospede(hospede);
		return this;
	}
	
	private DateTime parseData(String stringDaData) {
		DateTime data = new ParserDeStringParaData().parseData(stringDaData);
		return data;
	}

	public FakeReserva noQuarto(String numero) {
		Quarto quarto = new Quarto();
		quarto.setNumero(numero);
		return this;
	}
	

}

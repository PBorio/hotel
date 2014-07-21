package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import domain.Quarto;

public class HotelCalendario {

	LinhasDoCalendario cabecalho;
	
	public List<LinhasDoCalendario> linhas = new ArrayList<LinhasDoCalendario>();

	private final List<Quarto> quartos;

	private DateTime primeiraSegundaFeira;

	public HotelCalendario(List<Quarto> quartos, DateTime hoje) {
		this.quartos = quartos;
		this.primeiraSegundaFeira = hoje.dayOfWeek().withMinimumValue().withTimeAtStartOfDay(); 
		preencherLinhasDoCalendario();
	}
	
	public Integer getMesAtual(){
		return primeiraSegundaFeira.monthOfYear().get();
	}
	
	public Integer getAnoAtual(){
		return primeiraSegundaFeira.year().get();

	}
	
	public Integer getPrimeiraSegundaFeira(){
		return primeiraSegundaFeira.dayOfMonth().get();
	}

	public String getNomeDoMesAtual() {
		return primeiraSegundaFeira.monthOfYear().getAsText();
	}
	
	public String getNomeDoProximoMes() {
		DateTime ultimoSegundaFeira = primeiraSegundaFeira.plusDays(28).withTimeAtStartOfDay();
		if (ultimoSegundaFeira.monthOfYear().equals(primeiraSegundaFeira.monthOfYear()))
			return "";
		return ultimoSegundaFeira.monthOfYear().getAsText();
	}
	
	public Integer getProximoMes(){
		DateTime ultimoSegundaFeira = primeiraSegundaFeira.plusDays(28).withTimeAtStartOfDay();
		return ultimoSegundaFeira.monthOfYear().get();
	}
	
	public Integer getProximoAno(){
		DateTime ultimoSegundaFeira = primeiraSegundaFeira.plusDays(28).withTimeAtStartOfDay();
		if (ultimoSegundaFeira.monthOfYear().equals(primeiraSegundaFeira.monthOfYear()))
			return null;
		return ultimoSegundaFeira.year().get();

	}
	
	public Integer getDiasRestantesMesAtual(){
		DateTime ultimoDiaDoMes = primeiraSegundaFeira.dayOfMonth().withMaximumValue();
		return Days.daysBetween(primeiraSegundaFeira, ultimoDiaDoMes).getDays()+1;
	}
	
	public Integer getDiasRestantesProximoMes(){
		DateTime ultimoSegundaFeira = primeiraSegundaFeira.plusDays(28).withTimeAtStartOfDay();
		DateTime primeroDiaDoMes = ultimoSegundaFeira.dayOfMonth().withMinimumValue();
		Integer dias = Days.daysBetween(primeroDiaDoMes, ultimoSegundaFeira).getDays()+1;
		return dias;
	}

	public List<LinhasDoCalendario> getLinhas() {
		return linhas;
	}
	
	public LinhasDoCalendario getCabecalho(){
		return this.cabecalho;
	}
	
	private void preencherLinhasDoCalendario() {

		DateTime ultimoDiaDoMes = primeiraSegundaFeira.plusDays(28).withTimeAtStartOfDay();
		DateTime dia = primeiraSegundaFeira;
		cabecalho = new LinhasDoCalendario();
		while (dia.isBefore(ultimoDiaDoMes)||dia.equals(ultimoDiaDoMes)){
			DiaCabecalho diaCabecalho = new DiaCabecalho(dia);
			cabecalho.addDia(diaCabecalho);
			dia = dia.plusDays(1).withTimeAtStartOfDay();
		}
		
		for (Quarto quarto : quartos){
			dia = primeiraSegundaFeira;
			LinhasDoCalendario linha = new LinhasDoCalendario(quarto);
			while (dia.isBefore(ultimoDiaDoMes)||dia.equals(ultimoDiaDoMes)){
				DiaMesCalendario diaCalendario = new DiaMesCalendario(quarto, dia);
				linha.addDia(diaCalendario);
				dia = dia.plusDays(1).withTimeAtStartOfDay();
			}
			linhas.add(linha);
		}
	}
}

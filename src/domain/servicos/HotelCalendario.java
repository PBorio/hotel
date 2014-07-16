package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import domain.Quarto;

public class HotelCalendario {

	private final DateTime hoje;
	
	LinhasDoCalendario cabecalho;
	
	public List<LinhasDoCalendario> linhas = new ArrayList<LinhasDoCalendario>();

	private final List<Quarto> quartos;

	public HotelCalendario(List<Quarto> quartos, DateTime hoje) {
		this.quartos = quartos;
		this.hoje = hoje;
		preencherLinhasDoCalendario();
	}

	public String getMes() {
		return hoje.monthOfYear().getAsText();
	}
	

	public List<LinhasDoCalendario> getLinhas() {
		return linhas;
	}
	
	public LinhasDoCalendario getCabecalho(){
		return this.cabecalho;
	}
	
	private void preencherLinhasDoCalendario() {
		DateTime dia = hoje.dayOfMonth().withMinimumValue();
		DateTime ultimoDiaDoMes = hoje.dayOfMonth().withMaximumValue();
		
		cabecalho = new LinhasDoCalendario();
		while (dia.isBefore(ultimoDiaDoMes)||dia.equals(ultimoDiaDoMes)){
			DiaCabecalho diaCabecalho = new DiaCabecalho(dia);
			cabecalho.addDia(diaCabecalho);
			dia = dia.plusDays(1);
		}
		
		for (Quarto quarto : quartos){
			dia = hoje.dayOfMonth().withMinimumValue();
			LinhasDoCalendario linha = new LinhasDoCalendario(quarto);
			while (dia.isBefore(ultimoDiaDoMes)||dia.equals(ultimoDiaDoMes)){
				DiaMesCalendario diaCalendario = new DiaMesCalendario(quarto, dia);
				linha.addDia(diaCalendario);
				dia = dia.plusDays(1);
			}
			linhas.add(linha);
		}
	}
}

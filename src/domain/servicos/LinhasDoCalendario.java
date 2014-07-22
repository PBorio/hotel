package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import domain.Quarto;


public class LinhasDoCalendario  {
	private List<DiaMesCalendario> dias = new ArrayList<DiaMesCalendario>();
	private Quarto quarto;
	
	public LinhasDoCalendario(){}
	
	public LinhasDoCalendario(Quarto quarto) {
		this.quarto = quarto;
	}

	public void addDia(DiaMesCalendario dia){
		this.dias.add(dia);
	}

	public List<DiaMesCalendario> getDias() {
		return dias;
	}
	
	public String getNumeroQuarto(){
		if (quarto == null ) return "";
		
		return quarto.getNumero();
	}
}

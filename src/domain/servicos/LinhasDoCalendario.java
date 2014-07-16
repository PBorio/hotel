package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import domain.Quarto;
import domain.servicos.interfaces.DiaDoCalendario;


public class LinhasDoCalendario  {
	private List<DiaDoCalendario> dias = new ArrayList<DiaDoCalendario>();
	private Quarto quarto;
	
	public LinhasDoCalendario(){}
	
	public LinhasDoCalendario(Quarto quarto) {
		this.quarto = quarto;
	}

	public void addDia(DiaDoCalendario dia){
		this.dias.add(dia);
	}

	public List<DiaDoCalendario> getDias() {
		return dias;
	}
	
	public String getNumeroQuarto(){
		if (quarto == null ) return "";
		
		return quarto.getNumero();
	}
}

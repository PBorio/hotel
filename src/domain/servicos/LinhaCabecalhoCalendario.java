package domain.servicos;

import java.util.ArrayList;
import java.util.List;

public class LinhaCabecalhoCalendario {
	
	private List<DiaCabecalho> dias = new ArrayList<DiaCabecalho>();

	public void addDia(DiaCabecalho diaCabecalho) {
		this.dias.add(diaCabecalho);
	}

	public List<DiaCabecalho> getDias() {
		return dias;
	}

	
}

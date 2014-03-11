package domain.nulos;

import domain.Categoria;
import domain.Quarto;

public class QuartoNulo extends Quarto {
	
	@Override
	public String getNumero() {
		return "";
	}
	
	@Override
	public Categoria getCategoria() {
		return new CategoriaNulo();
	}

}

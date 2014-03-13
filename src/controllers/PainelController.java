package controllers;

import java.util.ArrayList;
import java.util.List;

import domain.Quarto;
import br.com.caelum.vraptor.Resource;

@Resource
public class PainelController {

	public List<Quarto> painel(){
		return new ArrayList<Quarto>();
	}
	
}

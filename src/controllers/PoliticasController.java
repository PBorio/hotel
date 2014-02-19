package controllers;

import java.util.List;

import repositorios.PoliticaPrecoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.PoliticaDePrecos;

@Resource
public class PoliticasController {
	
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Result result;

	public PoliticasController(PoliticaPrecoRepositorio politicaPrecoRepositorio, Result result){
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.result = result;
	}
	
	public List<PoliticaDePrecos> list(){
		return politicaPrecoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/categorias/{id}")
	public void edit(Long id) {
		
		PoliticaDePrecos politica = politicaPrecoRepositorio.buscaPorId(id);
		
		result.include("politicaDePrecos", politica);
		result.of(this).novo();
	}
	
	public void salva(PoliticaDePrecos politica){
		
		if (politica.getId() == null){
			politicaPrecoRepositorio.salva(politica);
		}else{
			politicaPrecoRepositorio.atualiza(politica);
		}
		
		result.include("mensagem", "Categoria salva com sucesso!");
		result.redirectTo(this).list();
	}

	public void novo() {
		result.include("politicaDePrecos", novaPolitica());
	}

	private PoliticaDePrecos novaPolitica() {
		return new PoliticaDePrecos();
	}
}

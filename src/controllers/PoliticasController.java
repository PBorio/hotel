package controllers;

import java.util.List;

import repositorios.CategoriaRepositorio;
import repositorios.PoliticaPrecoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Categoria;
import domain.PoliticaDePrecos;

@Resource
public class PoliticasController {
	
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Result result;
	private CategoriaRepositorio categoriaRepositorio;

	public PoliticasController(PoliticaPrecoRepositorio politicaPrecoRepositorio, CategoriaRepositorio categoriaRepositorio, Result result){
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.categoriaRepositorio = categoriaRepositorio;
		this.result = result;
	}
	
	public List<PoliticaDePrecos> list(){
		return politicaPrecoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/politicas/{id}")
	public void edit(Long id) {
		
		PoliticaDePrecos politica = politicaPrecoRepositorio.buscaPorId(id);
		
		List<Categoria> categoriaList = categoriaRepositorio.buscaTodos();
		result.include("categoriaList", categoriaList);	
		result.include("politicaDePrecos", politica);
		result.of(this).novo();
	}
	
	public void salva(PoliticaDePrecos politicaDePrecos){
		
		if (politicaDePrecos.getId() == null){
			politicaPrecoRepositorio.salva(politicaDePrecos);
		}else{
			politicaPrecoRepositorio.atualiza(politicaDePrecos);
		}
		
		result.include("mensagem", "Política salva com sucesso!");
		result.redirectTo(this).list();
	}

	public void novo() {
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
		result.include("politicaDePrecos", novaPolitica());
	}

	private PoliticaDePrecos novaPolitica() {
		return new PoliticaDePrecos();
	}
}

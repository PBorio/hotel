package controllers;

import java.util.List;

import repositorios.CategoriaRepositorio;
import repositorios.PoliticaPrecoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.validators.PoliticaValidation;
import domain.Categoria;
import domain.PoliticaDePrecos;

@Resource
public class PoliticasController {
	
	private PoliticaPrecoRepositorio politicaPrecoRepositorio;
	private Result result;
	private CategoriaRepositorio categoriaRepositorio;
	private Validator validator;

	public PoliticasController(PoliticaPrecoRepositorio politicaPrecoRepositorio, CategoriaRepositorio categoriaRepositorio, Result result, Validator validator){
		this.politicaPrecoRepositorio = politicaPrecoRepositorio;
		this.categoriaRepositorio = categoriaRepositorio;
		this.result = result;
		this.validator = validator;
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
		
		validarPolitica(politicaDePrecos);
		if (validator.hasErrors()){
			result.include("politicaDePrecos", politicaDePrecos);
			result.include("categoriaList",this.categoriaRepositorio.buscaTodos());
		    validator.onErrorUsePageOf(this).novo();
		}
		
		try{
			if (politicaDePrecos.getId() == null){
				politicaPrecoRepositorio.salva(politicaDePrecos);
			}else{
				politicaPrecoRepositorio.atualiza(politicaDePrecos);
			}
			
			result.include("mensagem", "Política salva com sucesso!");
			result.redirectTo(this).list();
		}catch(Exception e){
			e.printStackTrace();
			result.include(politicaDePrecos);
			result.include("categoriaList", categoriaRepositorio.buscaTodos());
			validator. add(new ValidationMessage(e.getMessage(),"erro.na.política",e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
	}

	private void validarPolitica(PoliticaDePrecos politicaDePrecos) {
		List<PoliticaDePrecos> politicasExistentes = politicaPrecoRepositorio.buscaTodos();
		PoliticaValidation politicaValidation = new PoliticaValidation(validator, politicasExistentes);
		validator = politicaValidation.validar(politicaDePrecos);
	}

	public void novo() {
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
		result.include("politicaDePrecos", novaPolitica());
	}

	private PoliticaDePrecos novaPolitica() {
		return new PoliticaDePrecos();
	}
}

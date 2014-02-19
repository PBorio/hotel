package controllers;

import java.util.List;

import repositorios.CategoriaRepositorio;
import repositorios.QuartoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Categoria;
import domain.Quarto;


@Resource
public class QuartosController {
	
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
//	private Validator validator;

	public QuartosController(Result result, QuartoRepositorio quartoRepositorio, CategoriaRepositorio categoriaRepositorio) {
		this.result = result;
		this.quartoRepositorio = quartoRepositorio;
//		this.validator = validator;
		this.categoriaRepositorio = categoriaRepositorio;
	}
	
	@Get
	public List<Quarto> list() {
		return quartoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/quartos/{id}")
	public void edit(Long id) {
		
		Quarto quarto = quartoRepositorio.buscaPorId(id);
		List<Categoria> categoriaList = categoriaRepositorio.buscaTodos();
		
		result.include("categoriaList", categoriaList);
		result.include("quarto", quarto);
		result.of(this).novo();
	}
	
	public void salva(Quarto quarto){
		
		if (quarto.getId() == null){
			quartoRepositorio.salva(quarto);
		}else{
			quartoRepositorio.atualiza(quarto);
		}
		
		result.include("mensagem", "Quarto salvo com sucesso!");
		result.redirectTo(this).list();
	}

	public void novo() {
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
		result.include("quarto", novoQuarto());
	}

	private Quarto novoQuarto() {
		return new Quarto();
	}

}

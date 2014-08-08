package controllers;

import java.util.List;

import repositorios.CategoriaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.Categoria;
import domain.exceptions.HotelException;

@Resource
public class CategoriasController {
	
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;
	private Validator validator;

	public CategoriasController(CategoriaRepositorio categoriaRepositorio, Result result, Validator validator){
		this.categoriaRepositorio = categoriaRepositorio;
		this.result = result;
		this.validator = validator;
	}
	
	@Get
	public List<Categoria> list() {
		return categoriaRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/categorias/{id}")
	public void edit(Long id) {
		
		Categoria categoria = categoriaRepositorio.buscaPorId(id);
		
		result.include("categoria", categoria);
		result.include("editando", true);
		result.of(this).novo();
	}
	
	public void salva(Categoria categoria){
		validarCategoria(categoria);
		if (validator.hasErrors()){
			result.include("categoria", categoria);
		    validator.onErrorUsePageOf(this).novo();
		}
		
		try{
			if (categoria.getId() == null){
				categoriaRepositorio.salva(categoria);
			}else{
				categoriaRepositorio.atualiza(categoria);
			}
			
			result.include("mensagem", "Categoria salva com sucesso!");
			result.redirectTo(this).list();
		}catch(HotelException e){
			e.printStackTrace();
			result.include(categoria);
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.quarto",e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
	}

	private void validarCategoria(Categoria categoria) {
		if (categoria.getDescricao() == null || categoria.getDescricao().trim().equals(""))
			validator.add(new ValidationMessage("Descrição da categoria é obrigatória", "categoria"));
	}

	public void novo() {
		result.include("categoria", novaCategoria());
	}

	private Categoria novaCategoria() {
		return new Categoria();
	}

}

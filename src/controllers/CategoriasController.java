package controllers;

import java.util.List;

import repositorios.CategoriaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Categoria;

@Resource
public class CategoriasController {
	
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;

	public CategoriasController(CategoriaRepositorio categoriaRepositorio, Result result){
		this.categoriaRepositorio = categoriaRepositorio;
		this.result = result;
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
		
		if (categoria.getId() == null){
			categoriaRepositorio.salva(categoria);
		}else{
			categoriaRepositorio.atualiza(categoria);
		}
		
		result.include("mensagem", "Categoria salva com sucesso!");
		result.redirectTo(this).list();
	}

	public void novo() {
		result.include("categoria", novaCategoria());
	}

	private Categoria novaCategoria() {
		return new Categoria();
	}

}

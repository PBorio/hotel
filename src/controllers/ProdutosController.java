package controllers;

import java.util.List;

import repositorios.ProdutoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.Produto;
import domain.exceptions.HotelException;

@Resource
public class ProdutosController {
	
	private ProdutoRepositorio produtoRepositorio;
	private Result result;
	private Validator validator;
	
	public ProdutosController(ProdutoRepositorio produtoRepositorio, Result result, Validator validator){
		this.produtoRepositorio = produtoRepositorio;
		this.result = result;
		this.validator = validator;
	}

	
	@Get
	public List<Produto> list() {
		return produtoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/produtos/{id}")
	public void edit(Long id) {
		
		Produto produto = produtoRepositorio.buscaPorId(id);
		
		result.include("produto", produto);
		result.include("editando", true);
		result.of(this).novo();
	}
	
	public void salva(Produto produto){
		validarProduto(produto);
		if (validator.hasErrors()){
			result.include("produto", produto);
		    validator.onErrorUsePageOf(this).novo();
		}
		
		try{
			if (produto.getId() == null){
				produtoRepositorio.salva(produto);
			}else{
				produtoRepositorio.atualiza(produto);
			}
			result.include("mensagem", "Produto salvo com sucesso!");
			result.redirectTo(this).list();
		}catch(HotelException e){
			e.printStackTrace();
			result.include(produto);
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.produto",e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
	}

	private void validarProduto(Produto produto) {
		if (produto.getDescricao() == null || produto.getDescricao().trim().equals(""))
			validator.add(new ValidationMessage("Descrição do produto é obrigatória", "categoria"));
		
		if (produto.getPreco() == null)
			validator.add(new ValidationMessage("Preço do produto é obrigatório", "categoria"));
	}

	public void novo() {
		result.include("produto", novoProduto());
	}

	private Produto novoProduto() {
		return new Produto();
	}

}

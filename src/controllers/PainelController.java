package controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import repositorios.EstadiaRepositorio;
import repositorios.ProdutoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Consumo;
import domain.Estadia;

@Resource
public class PainelController {

	private EstadiaRepositorio estadiaRepositorio;
	private Result result;
	private ProdutoRepositorio produtoRepositorio;

	public PainelController(EstadiaRepositorio estadiaRepositorio, ProdutoRepositorio produtoRepositorio, Result result){
		this.estadiaRepositorio = estadiaRepositorio;
		this.produtoRepositorio = produtoRepositorio;
		this.result = result;
		
	}
	
	@Get
	@Path("/")
	public List<Estadia> painel(){
		List<Estadia> estadias = estadiaRepositorio.estadiasAbertas();
		return estadias;
	}
	
	@Get
	@Path("/painel/{id}")
	public void estadia(Long id) {
		Estadia estadia = estadiaRepositorio.buscaPorId(id);
		result.include("estadia", estadia);
	}
	
	@Post("/painel/add/consumo/")
	public void addConsumo(Consumo consumo){
		Estadia estadia = estadiaRepositorio.buscaPorId(consumo.getEstadia().getId());
		result.include("estadia", estadia);
	}
	
	@Post("/painel/add/servico/")
	public void addServico(Consumo consumo){
		
	}
	
	@Get
	@Path("/painel/buscarProdutoPorDescricao.json")
	public void buscarProdutoPorDescricao(String descricao) {
			result.use(json()).withoutRoot()
					.from(produtoRepositorio.buscaProdutosPorDescricao(descricao))
					.serialize();
		
	}
}

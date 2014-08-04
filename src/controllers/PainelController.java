package controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import repositorios.EstadiaRepositorio;
import repositorios.ProdutoRepositorio;
import repositorios.ServicoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Consumo;
import domain.Estadia;
import domain.ServicoPrestado;

@Resource
public class PainelController {

	private EstadiaRepositorio estadiaRepositorio;
	private Result result;
	private ProdutoRepositorio produtoRepositorio;
	private ServicoRepositorio servicoRepositorio;

	public PainelController(EstadiaRepositorio estadiaRepositorio, 
							ProdutoRepositorio produtoRepositorio,
							ServicoRepositorio servicoRepositorio,
							Result result){
		this.estadiaRepositorio = estadiaRepositorio;
		this.produtoRepositorio = produtoRepositorio;
		this.servicoRepositorio = servicoRepositorio;
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
		System.out.println(consumo);
	}
	
	@Post("/painel/add/servico/")
	public void addServico(ServicoPrestado servicoPrestado){
		System.out.println(servicoPrestado);
	}
	
	@Get
	@Path("/painel/buscarProdutosPorDescricao.json")
	public void buscarProdutosPorDescricao(String descricao) {
			result.use(json()).withoutRoot()
					.from(produtoRepositorio.buscaProdutosPorDescricao(descricao))
					.serialize();
	}
	
	@Get
	@Path("/painel/buscarProdutoComEstaDescricao.json")
	public void buscarProdutoComEstaDescricao(String descricao) {
			result.use(json()).withoutRoot()
					.from(produtoRepositorio.buscaPorEstaDescricao(descricao))
					.serialize();
	}
	
	@Get
	@Path("/painel/buscarServicosPorDescricao.json")
	public void buscarServicosPorDescricao(String descricao) {
			result.use(json()).withoutRoot()
					.from(servicoRepositorio.buscaServicosPorDescricao(descricao))
					.serialize();
	}
	
	@Get
	@Path("/painel/buscarServicoComEstaDescricao.json")
	public void buscarServicoComEstaDescricao(String descricao) {
			result.use(json()).withoutRoot()
					.from(servicoRepositorio.buscaPorEstaDescricao(descricao))
					.serialize();
	}
}

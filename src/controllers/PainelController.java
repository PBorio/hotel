package controllers;

import java.util.List;

import repositorios.EstadiaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Estadia;

@Resource
public class PainelController {

	private EstadiaRepositorio estadiaRepositorio;
	private Result result;

	public PainelController(EstadiaRepositorio estadiaRepositorio, Result result){
		this.estadiaRepositorio = estadiaRepositorio;
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
	
}

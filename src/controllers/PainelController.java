package controllers;

import java.util.ArrayList;
import java.util.List;

import repositorios.EstadiaRepositorio;
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
	
	public List<Estadia> painel(){
		List<Estadia> estadias = estadiaRepositorio.estadiasAbertas();
		return estadias;
	}
	
}

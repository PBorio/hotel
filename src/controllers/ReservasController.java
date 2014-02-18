package controllers;

import repositorios.CategoriaRepositorio;
import controllers.views.reservas.ReservasView;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ReservasController {
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;

	public ReservasController(Result result, CategoriaRepositorio categoriaRepositorio){
		this.result = result;
		this.categoriaRepositorio = categoriaRepositorio;
	}
	
	public void reserva(){
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
	}
	
	public void salva(ReservasView reservasView){
		System.out.println(reservasView);
	}

}

package controllers;

import java.util.ArrayList;
import java.util.List;

import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Reserva;

@Resource
public class CheckinController {
	
	private ReservaRepositorio reservaRepositorio;
	private Result result;

	public CheckinController(Result result, ReservaRepositorio reservaRepositorio) {
		this.result = result;
		this.reservaRepositorio = reservaRepositorio;
		
	}
	
	public List<Reserva> list(){
		return reservaRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/checkin/pesquisar")
	public void pesquisar(String pesquisa,Long id) {
		
		List<Reserva> reservaList = new ArrayList<Reserva>();
		
	   reservaList.addAll(reservaRepositorio.buscarPorNomeDoHospede(pesquisa));
		
		result.include("reservaList",reservaList );
		result.of(this).list();
	}

}

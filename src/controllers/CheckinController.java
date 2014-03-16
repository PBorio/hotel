package controllers;

import java.util.List;

import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Resource;
import domain.Reserva;

@Resource
public class CheckinController {
	
	private ReservaRepositorio reservaRepositorio;

	public CheckinController(ReservaRepositorio reservaRepositorio) {
		this.reservaRepositorio = reservaRepositorio;
	}
	
	public List<Reserva> list(){
		return reservaRepositorio.buscaTodos();
	}

}

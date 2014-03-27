package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import repositorios.EstadiaRepositorio;
import repositorios.HospedeRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Estadia;
import domain.Hospede;
import domain.Reserva;
import domain.servicos.Checkin;

@Resource
public class CheckinController {
	
	private ReservaRepositorio reservaRepositorio;
	private Result result;
	private HospedeRepositorio hospedeRepositorio;
	private EstadiaRepositorio estadiaRepositorio;

	public CheckinController(Result result, 
							 ReservaRepositorio reservaRepositorio, 
							 HospedeRepositorio hospedeRepositorio,
							 EstadiaRepositorio estadiaRepositorio) {
		this.result = result;
		this.reservaRepositorio = reservaRepositorio;
		this.hospedeRepositorio = hospedeRepositorio;
		this.estadiaRepositorio = estadiaRepositorio;
	}
	
	public List<Reserva> list(){
		return reservaRepositorio.buscaReservasEmAberto();
	}
	
	@Get
	@Path("/checkin/pesquisar")
	public void pesquisar(String pesquisa) {
		
		List<Reserva> reservaList = new ArrayList<Reserva>();
		
	   reservaList.addAll(reservaRepositorio.buscarPorNomeDoHospede(pesquisa));
		
		result.include("reservaList",reservaList );
		result.of(this).list();
	}
	
	public Reserva checkin(){
		return new Reserva();
	}
	
	@Get
	@Path("/checkin/{id}")
	public void edit(Long id) {
		
		Reserva reserva = reservaRepositorio.buscaPorId(id);
		Estadia estadia = Checkin.checkinAPartirDeUmaReserva(reserva).iniciarEstadiaAPartirDeUmaReserva();
		
		result.include("estadia", estadia);
		result.include("hospede", reserva.getHospede());
		result.of(this).checkin();
	}
	
	public void salvaEPreparaMaisHospedes(Estadia estadia, Hospede hospede){
		
		if (hospede.getId() == null){
			hospedeRepositorio.salva(hospede);
		}else{
			hospedeRepositorio.atualiza(hospede);
		}
		
		estadia.addHospede(hospede);
		if (estadia.getId() == null){
			estadia.setDataCheckin(new DateTime());
			estadiaRepositorio.salva(estadia);
		}else{
			estadiaRepositorio.atualiza(estadia);
		}
		
		result.include("estadia", estadia);
		result.include("hospede", new Hospede());
		result.include("mensagem", "Estadia criada com sucesso!");
		result.of(this).checkin();
	}
	
	public void salva(Estadia estadia, Hospede hospede){
		
		if (hospede.getId() == null){
			hospedeRepositorio.salva(hospede);
		}else{
			hospedeRepositorio.atualiza(hospede);
		}
		
		if (estadia.getId() != null)
			estadia = estadiaRepositorio.buscaPorId(estadia.getId());
		
		estadia.addHospede(hospede);
		if (estadia.getId() == null){
			estadia.setDataCheckin(new DateTime());
			estadiaRepositorio.salva(estadia);
		}else{
			estadiaRepositorio.atualiza(estadia);
		}
		
		result.include("mensagem", "Estadia criada com sucesso!");
		result.redirectTo(PainelController.class).painel();
	}

}

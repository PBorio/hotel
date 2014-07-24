package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import repositorios.EstadiaRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Estadia;
import domain.Hospede;
import domain.Reserva;
import domain.servicos.Checkin;
import domain.servicos.interfaces.HospedeService;

@Resource
public class CheckinController {
	
	private ReservaRepositorio reservaRepositorio;
	private Result result;
	private EstadiaRepositorio estadiaRepositorio;
	private HospedeService hospedeService;

	public CheckinController(Result result, 
							 ReservaRepositorio reservaRepositorio, 
							 HospedeService hospedeService,
							 EstadiaRepositorio estadiaRepositorio) {
		this.result = result;
		this.reservaRepositorio = reservaRepositorio;
		this.hospedeService = hospedeService;
		this.estadiaRepositorio = estadiaRepositorio;
	}
	
	public List<Reserva> list(){
		List<Reserva> list =  reservaRepositorio.buscaReservasEmAberto();
		return list;
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
	@Path("/checkin/{idReserva}")
	public void edit(Long idReserva, Long idQuarto) {
		
		Reserva reserva = reservaRepositorio.buscaPorId(idReserva);
		Estadia estadia = Checkin.checkinAPartirDeUmaReserva(reserva).iniciarEstadiaAPartirDeUmaReserva();
		
		result.include("reserva", reserva);
		result.include("estadia", estadia);
		result.include("hospede", reserva.getHospede());
		result.of(this).checkin();
	}
	
	public void salvaEPreparaMaisHospedes(Estadia estadia, Hospede hospede){
		
		Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede); 
		
		estadia.addHospede(hospedeExistente);
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
		
		Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede); 
		
		if (estadia.getId() != null)
			estadia = estadiaRepositorio.buscaPorId(estadia.getId());
		
		estadia.addHospede(hospedeExistente);
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

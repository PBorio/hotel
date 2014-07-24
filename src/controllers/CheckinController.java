package controllers;

import java.util.ArrayList;
import java.util.List;

import repositorios.EstadiaRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import controllers.validators.HospedeValidation;
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
	private Validator validator;
	private Checkin checkin;

	public CheckinController(Result result,
						     Validator validator,
							 ReservaRepositorio reservaRepositorio, 
							 HospedeService hospedeService,
							 EstadiaRepositorio estadiaRepositorio, 
							 Checkin checkin) {
		this.result = result;
		this.validator = validator;
		this.reservaRepositorio = reservaRepositorio;
		this.hospedeService = hospedeService;
		this.estadiaRepositorio = estadiaRepositorio;
		this.checkin = checkin;
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
	public void edit(Long idReserva) {
		
		Reserva reserva = reservaRepositorio.buscaPorId(idReserva);
		
		checkin.clear();
		checkin.aPartirDaReserva(reserva);
		
		result.include("reserva", reserva);
		result.include("hospedeReserva", reserva.getHospede());
		result.of(this).checkin();
	}
	
	@Get
	@Path("/checkin/remover/{id}")
	public void removerHospede(Long id) {
		
		Hospede hospede = new Hospede();
		hospede.setId(id);
		checkin.removeHospede(hospede);
		
		result.of(this).checkin();
	}
	
	@Get
	@Path("/checkin/responsavel/estadia/")
	public void hospedeResponsavelNaEstadia(){
		checkin.addHospede(checkin.getReserva().getHospede());
		result.of(this).checkin();
	}
	
	public void salvaEPreparaMaisHospedes(Estadia estadia, Hospede hospede){

		HospedeValidation validation = new HospedeValidation(validator);
		validation.validarHospede(hospede);
		
		if (validator.hasErrors()){
			result.include("estadia",estadia);
		    validator.onErrorUsePageOf(this).checkin();
		}
		
		Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede); 
		checkin.addHospede(hospedeExistente);
		result.of(this).checkin();
	}

	public void salva(Hospede hospede){
		HospedeValidation validation = new HospedeValidation(validator);
		validation.validarHospede(hospede);
		if (validator.hasErrors()){
		    validator.onErrorUsePageOf(this).checkin();
		}
		Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede); 
		checkin.addHospede(hospedeExistente);
		
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		estadiaRepositorio.salva(estadia);
		
		checkin.clear();
		result.include("mensagem", "Estadia criada com sucesso!");
		result.redirectTo(PainelController.class).painel();
	}
}

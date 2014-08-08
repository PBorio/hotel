package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import repositorios.EstadiaRepositorio;
import repositorios.PoliticaPrecoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import controllers.validators.HospedeValidation;
import domain.Estadia;
import domain.Hospede;
import domain.Pagamento;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.helpers.ValidadorPagamentoReserva;
import domain.servicos.CalculoDeValorDaDiariaService;
import domain.servicos.Checkin;
import domain.servicos.ModificadorDeValoresDaDiariaService;
import domain.servicos.helpers.Periodo;
import domain.servicos.interfaces.HospedeService;

@Resource
public class CheckinController {
	
	private ReservaRepositorio reservaRepositorio;
	private Result result;
	private EstadiaRepositorio estadiaRepositorio;
	private HospedeService hospedeService;
	private Validator validator;
	private Checkin checkin;
	private PoliticaPrecoRepositorio politicaRepositorio;

	public CheckinController(Result result,
						     Validator validator,
							 ReservaRepositorio reservaRepositorio, 
							 HospedeService hospedeService,
							 EstadiaRepositorio estadiaRepositorio, 
							 PoliticaPrecoRepositorio politicaRepositorio,
							 Checkin checkin) {
		this.result = result;
		this.validator = validator;
		this.reservaRepositorio = reservaRepositorio;
		this.hospedeService = hospedeService;
		this.estadiaRepositorio = estadiaRepositorio;
		this.politicaRepositorio = politicaRepositorio;
		this.checkin = checkin;
	}
	
	public List<Reserva> list(){
		List<Reserva> list =  reservaRepositorio.buscaReservasEmAberto();
		return list;
	}
	
	@Get
	@Path("/checkin/pesquisar/")
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
		
		Reserva reserva = reservaRepositorio.buscaReservaPorIdComPagamentos(idReserva);
		
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
	
	@Post("/checkin/novo/hospede")
	public void salvaHospede(Hospede hospede){

		HospedeValidation validation = new HospedeValidation(validator);
		validation.validarHospede(hospede);
		
		if (validator.hasErrors()){
		    validator.onErrorUsePageOf(this).checkin();
		}
		
		Hospede hospedeExistente = hospedeService.buscarESalvarOuAtualizar(hospede); 
		checkin.addHospede(hospedeExistente);
		
		result.of(this).checkin();
	}

	@Post("/checkin/continua/para/valores")
	public void checkinValores(){
		if (checkin.getHospedes().isEmpty()){
			validator.add(new ValidationMessage("Nenhum hóspede informado", "checkin"));
			validator.onErrorUsePageOf(this).checkin();
		}
	}
	
	@Post("/checkin/recalcula/valores/")
	public void recalculaValores(Checkin checkinLocal){
		recalcular(checkinLocal);
		result.of(this).checkinValores();
	}
	
	@Post("/checkin/ir/para/pagamento/")
	public void checkinPagamento(Checkin checkinLocal){
		recalcular(checkinLocal);
	}
	
	@Post("/checkin/registrar/pagamento")
	public void registrarPagamento(Pagamento pagamento){
		
		try{
			Estadia estadia = recalcular(checkin);
			
			if (pagamento.foiMarcado()){
				pagamento.arrumaValores();
				ValidadorPagamentoReserva validador = new ValidadorPagamentoReserva(pagamento);
				validador.validar();
				estadia.addPagamento(pagamento);
			}
			estadiaRepositorio.salva(estadia);
			
			this.checkin.clear();
			result.include("mensagem", "Estadia criada com sucesso!");
			result.redirectTo(PainelController.class).painel();
		}catch(HotelException e){
			e.printStackTrace();
			result.include("pagamento", pagamento);
			validator. add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorUsePageOf(this).checkinPagamento(checkin); 
		}
	}

	private Estadia recalcular(Checkin checkinLocal) {
		this.checkin.setDataCheckin(checkinLocal.getDataCheckin());
		this.checkin.setDataCheckout(checkinLocal.getDataCheckout());
		this.checkin.setDesconto(checkinLocal.getDesconto());
		
		CalculoDeValorDaDiariaService calculoDiaria = new CalculoDeValorDaDiariaService(politicaRepositorio.buscaTodos());
		Periodo periodo = new Periodo(new DateTime(this.checkin.getDataCheckin()), new DateTime(this.checkin.getDataCheckout()));
		Double valorDiaria = calculoDiaria.calcularValorDaDiaria(periodo, checkin.getQuarto());
		valorDiaria = new ModificadorDeValoresDaDiariaService().aplicarModificadores(this.checkin.getReserva(), valorDiaria);
		this.checkin.setValorDiaria(valorDiaria);
		
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		return estadia;
	}
}

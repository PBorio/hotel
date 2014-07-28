package controllers;

import repositorios.PagamentoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.PagamentoReserva;
import domain.Reserva;
import domain.exceptions.HotelException;
import domain.helpers.ValidadorPagamentoReserva;

@Resource
public class PagamentosController {

	private final PagamentoRepositorio pagamentoRepositorio;
	private final Result result;
	private ReservaRepositorio reservaRepositorio;
	private Validator validator;

	public PagamentosController(PagamentoRepositorio pagamentoRepositorio, ReservaRepositorio reservaRepositorio, Result result, Validator validator){
		this.pagamentoRepositorio = pagamentoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.result = result;
		this.validator = validator;
	}
	
	public void registrar(PagamentoReserva pagamentoReserva){
		try{
			pagamentoReserva.arrumaValores();
			ValidadorPagamentoReserva validador = new ValidadorPagamentoReserva(pagamentoReserva);
			validador.validar();
			
			if (pagamentoReserva.getId() == null)
				pagamentoRepositorio.salva(pagamentoReserva);
			else
				pagamentoRepositorio.atualiza(pagamentoReserva);
			
			result.redirectTo(ConsultasController.class).consulta();
		}catch(HotelException e){
			e.printStackTrace();
			validator. add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorRedirectTo(ConsultasController.class).pagamento(pagamentoReserva.getReserva().getId());
		}
	}
	
	@Get
	@Path("/pagamentos/excluir/{id}")
	public void excluir(Long id){
		PagamentoReserva pagamento = pagamentoRepositorio.buscaPorId(id);
		Reserva reserva = reservaRepositorio.buscaPorId(pagamento.getReserva().getId());
		pagamentoRepositorio.excluir(pagamento);
		result.redirectTo(ConsultasController.class).pagamento(reserva.getId());
	}
	
	@Get
	@Path("/pagamentos/editar/{id}")
	public void editar(Long id){
		PagamentoReserva pagamento = pagamentoRepositorio.buscaPorId(id);
		Reserva reserva = reservaRepositorio.buscaPorId(pagamento.getReserva().getId());
		result.include("reserva", reserva);
		result.include("pagamentoReserva", pagamento);
		result.of(ConsultasController.class).pagamento(pagamento.getReserva().getId());
	}
}

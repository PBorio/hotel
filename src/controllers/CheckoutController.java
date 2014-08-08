package controllers;

import org.joda.time.DateTime;

import repositorios.EstadiaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.Estadia;
import domain.Pagamento;
import domain.exceptions.HotelException;
import domain.helpers.ValidadorPagamentoReserva;

@Resource
public class CheckoutController {

	private Validator validator;
	private Result result;
	private EstadiaRepositorio estadiaRepositorio;

	public CheckoutController(Validator validator, Result result, EstadiaRepositorio estadiaRepositorio){
		this.validator = validator;
		this.result = result;
		this.estadiaRepositorio = estadiaRepositorio;
	}
	
	@Get
	@Path("/checkout/{id}")
	public void checkout(Long id){
		Estadia e = estadiaRepositorio.buscaPorId(id);
		result.include("estadia", e);
	}
	
	@Post("/checkout/fechar")
	public void fechar(Estadia estadia){
		estadia = estadiaRepositorio.buscaPorId(estadia.getId());
		estadia.fechar(new DateTime());
		estadiaRepositorio.atualiza(estadia);
		result.redirectTo(PainelController.class).painel();
	}
	
	@Post("/checkout/registrar/pagamento")
	public void registrarPagamento(Pagamento pagamento, Estadia estadia){
		estadia = estadiaRepositorio.buscaPorId(estadia.getId());
		try{
			if (pagamento.foiMarcado()){
				pagamento.arrumaValores();
				ValidadorPagamentoReserva validador = new ValidadorPagamentoReserva(pagamento);
				validador.validar();
				estadia.addPagamento(pagamento);
			}
			estadiaRepositorio.atualiza(estadia);
			
			result.include("mensagem", "Pagamento gerado com sucesso!");
			result.redirectTo(this).checkout(estadia.getId());
		}catch(HotelException e){
			e.printStackTrace();
			result.include("pagamento", pagamento);
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.reserva",e.getMessage()));
			validator.onErrorForwardTo(this).checkout(estadia.getId()); 
		}
	}
}

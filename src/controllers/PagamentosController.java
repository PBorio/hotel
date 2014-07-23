package controllers;

import repositorios.PagamentoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.PagamentoReserva;
import domain.Reserva;

@Resource
public class PagamentosController {

	private final PagamentoRepositorio pagamentoRepositorio;
	private final Result result;
	private ReservaRepositorio reservaRepositorio;

	public PagamentosController(PagamentoRepositorio pagamentoRepositorio, ReservaRepositorio reservaRepositorio, Result result){
		this.pagamentoRepositorio = pagamentoRepositorio;
		this.reservaRepositorio = reservaRepositorio;
		this.result = result;
	}
	
	public void registrar(PagamentoReserva pagamentoReserva){
		pagamentoReserva.arrumaValores();
		pagamentoRepositorio.salva(pagamentoReserva);
		result.redirectTo(ConsultasController.class).consulta();
	}
	
	@Get
	@Path("/pagamentos/excluir/{id}")
	public void excluir(Long id){
		PagamentoReserva pagamento = pagamentoRepositorio.buscaPorId(id);
		Reserva reserva = reservaRepositorio.buscaPorId(pagamento.getReserva().getId());
		pagamentoRepositorio.excluir(pagamento);
		result.redirectTo(ConsultasController.class).show(reserva.getId());
	}
}

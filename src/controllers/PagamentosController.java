package controllers;

import java.util.Date;

import domain.PagamentoReserva;
import repositorios.PagamentoRepositorio;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PagamentosController {

	private final PagamentoRepositorio pagamentoRepositorio;
	private final Result result;

	public PagamentosController(PagamentoRepositorio pagamentoRepositorio, Result result){
		this.pagamentoRepositorio = pagamentoRepositorio;
		this.result = result;
	}
	
	public void registrar(PagamentoReserva pagamentoReserva){
		pagamentoReserva.setDataPagamento(new Date());
		pagamentoRepositorio.salva(pagamentoReserva);
		result.redirectTo(ConsultasController.class).consulta();
	}
}

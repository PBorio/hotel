package controllers;

import java.util.List;

import repositorios.ServicoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.Servico;
import domain.exceptions.HotelException;

@Resource
public class ServicosController {
	
	private ServicoRepositorio servicoRepositorio;
	private Result result;
	private Validator validator;
	
	public ServicosController(ServicoRepositorio servicoRepositorio, Result result, Validator validator){
		this.servicoRepositorio = servicoRepositorio;
		this.result = result;
		this.validator = validator;
	}

	
	@Get
	public List<Servico> list() {
		return servicoRepositorio.buscaTodos();
	}
	
	@Get
	@Path("/servicos/{id}")
	public void edit(Long id) {
		
		Servico servico = servicoRepositorio.buscaPorId(id);
		
		result.include("servico", servico);
		result.include("editando", true);
		result.of(this).novo();
	}
	
	public void salva(Servico servico){
		validarServico(servico);
		if (validator.hasErrors()){
			result.include("servico", servico);
		    validator.onErrorUsePageOf(this).novo();
		}
		
		try{
			if (servico.getId() == null){
				servicoRepositorio.salva(servico);
			}else{
				servicoRepositorio.atualiza(servico);
			}
			result.include("mensagem", "Serviço salvo com sucesso!");
			result.redirectTo(this).list();
		}catch(HotelException e){
			e.printStackTrace();
			result.include(servico);
			validator.add(new ValidationMessage(e.getMessage(),"erro.no.servico",e.getMessage()));
			validator.onErrorUsePageOf(this).novo();
		}
	}

	private void validarServico(Servico servico) {
		if (servico.getDescricao() == null || servico.getDescricao().trim().equals(""))
			validator.add(new ValidationMessage("Descrição do serviço é obrigatória", "categoria"));
		
		if (servico.getValorSugerido() == null)
			validator.add(new ValidationMessage("Valor do serviço é obrigatório", "categoria"));
	}

	public void novo() {
		result.include("servico", novoServico());
	}

	private Servico novoServico() {
		return new Servico();
	}

}

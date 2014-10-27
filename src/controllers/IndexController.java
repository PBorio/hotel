package controllers;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import repositorios.SolicitacaoAcessoRepositorio;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import domain.SolicitacaoAcesso;


@Resource
public class IndexController {
	
	private Result result;
	private Validator validator;
	private SolicitacaoAcessoRepositorio solicitacaoAcessoRepositorio;

	public IndexController(Result result, Validator validator, SolicitacaoAcessoRepositorio solicitacaoAcessoRepositorio) {
		this.result = result;
		this.validator = validator;
		this.solicitacaoAcessoRepositorio = solicitacaoAcessoRepositorio;
	}
	
	@Get
	@Path("/")
	public void index(){
		result.redirectTo(ConsultasController.class).consulta();
	}
	
	@Post("/solicitar/acesso/")
	public void acesso(SolicitacaoAcesso acesso){
		
		if (acesso.getEmail() == null){
			validator.add(new ValidationMessage("Email do Hospede é obrigatorio", "email"));
		}else{
			Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
		    Matcher m = p.matcher(acesso.getEmail());
		    if (!m.find()){
		    	validator.add(new ValidationMessage("Email inválido", "email"));
		     }
		}
		
		if (validator.hasErrors())
			validator.onErrorUsePageOf(this).index();
		
		acesso.setData(new Date());
		solicitacaoAcessoRepositorio.salva(acesso);
		result.include("acesso",acesso);
	}
	
}

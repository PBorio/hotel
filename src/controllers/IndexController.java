package controllers;

import domain.Acesso;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;


@Resource
public class IndexController {
	
	
	private Result result;

	public IndexController(Result result) {
		this.result = result;
	}
	@Get
	@Path("/")
	public void index(){
		
	}
	
	@Post("/solicitar/acesso/")
	public void acesso(Acesso acesso){
		String mensagem = "Sr(a)"+acesso.getNome()+", logo estaremos enviando as informações de acesso e instruções para o email: "+acesso.getEmail();
		result.include("mensagem",mensagem);
	}
}

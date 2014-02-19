package controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import domain.Quarto;
import domain.servicos.StatusDeReservasNoDia;


@Resource
public class IndexController {
	
	private Result result;

	public IndexController(Result result){
		this.result = result;
	}
	
	public void consulta(){
		Quarto q = new Quarto();
		q.setNumero("001");
		
		Quarto q2 = new Quarto();
		q2.setNumero("002");
		
		List<Quarto> qs = new ArrayList<Quarto>();
		qs.add(q);
		qs.add(q2);
		
		StatusDeReservasNoDia status = new StatusDeReservasNoDia(new DateTime(), qs);
		StatusDeReservasNoDia status2 = new StatusDeReservasNoDia(new DateTime(2014,2,20,0,0,0,0),qs);
		
		List<StatusDeReservasNoDia> statuses = new ArrayList<StatusDeReservasNoDia>();
		statuses.add(status);
		statuses.add(status2);
		
		result.include("statusDeReservaNoDiaList", statuses);
	}
}

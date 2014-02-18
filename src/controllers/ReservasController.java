package controllers;

import java.util.List;

import org.joda.time.DateTime;

import repositorios.CategoriaRepositorio;
import repositorios.HospedeRepositorio;
import repositorios.QuartoRepositorio;
import repositorios.ReservaRepositorio;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import controllers.views.reservas.ReservasView;
import domain.Categoria;
import domain.Hospede;
import domain.Quarto;
import domain.Reserva;

@Resource
public class ReservasController {
	
	private CategoriaRepositorio categoriaRepositorio;
	private Result result;
	private QuartoRepositorio quartoRepositorio;
	private HospedeRepositorio hospedeRepositorio;
	private ReservaRepositorio reservaRepositorio;

	public ReservasController(Result result, 
			                  CategoriaRepositorio categoriaRepositorio, 
			                  QuartoRepositorio quartoRepositorio, 
			                  HospedeRepositorio hospedeRepositorio,
			                  ReservaRepositorio reservaRepositorio){
		this.result = result;
		this.categoriaRepositorio = categoriaRepositorio;
		this.quartoRepositorio = quartoRepositorio;
		this.hospedeRepositorio = hospedeRepositorio;
		this.reservaRepositorio = reservaRepositorio;
	}
	
	public void reserva(){
		result.include("categoriaList", categoriaRepositorio.buscaTodos());
	}
	
	public void salva(ReservasView reservasView){
		
		Categoria categoria = categoriaRepositorio.buscaPorId(reservasView.getIdCategoria());
		List<Quarto> quartos = quartoRepositorio.buscaPorCategoria(categoria);
		
		Reserva reserva = new Reserva();
		reserva.setInicio(new DateTime(reservasView.getChegada().getTime()));
		reserva.setFim(new DateTime(reservasView.getSaida().getTime()));
		reserva.setNumeroAdultos(reservasView.getNumeroAdultos());
		reserva.setNumeroCriancas0a5(reservasView.getNumeroCriancas0a5());
		reserva.setNumeroCriancas6a16(reservasView.getNumeroCriancas6a16());
		reserva.setNumeroCriancas17a18(reservasView.getNumeroCriacas17a18());
		
		Hospede hospede = hospedeRepositorio.buscaPorEmail(reservasView.getEmailHospede());
			
		if (hospede == null){
			hospede = new Hospede();
			hospede.setNome(reservasView.getNomeHospede()+" "+reservasView.getSobrenomeHospede());
			hospede.setCidade(reservasView.getCidadeHospede());
			hospede.setEmail(reservasView.getEmailHospede());
			hospede.setTelefone(reservasView.getTelefoneHospede());
			hospede.setCelular(reservasView.getCelularHospede());
		}
		
		reserva.setHospede(hospede);
		
//		Quarto selecionado = null;
//		for (Quarto quarto : quartos){
//			for (Reserva r : quarto.getReservas()){
//				if (r.coincideCom(reserva))
//					sele
//			}
//		}
		
	}

}

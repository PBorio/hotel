package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import controllers.views.reservas.InformativoDeQuartos;
import domain.PoliticaDePrecos;
import domain.Quarto;
import domain.Reserva;

public class InformativoService {

	private List<PoliticaDePrecos> politicas;

	public InformativoService(List<PoliticaDePrecos> politicas) {
		this.politicas = politicas;
	}

	public List<InformativoDeQuartos> criarInformativoDeQuartos(Reserva reservaComTodosOsQuartos, List<Quarto> quartosDisponiveis) {
		CalculoDeValorDaDiariaService calculoValorDiaria = new CalculoDeValorDaDiariaService(politicas);
		List<InformativoDeQuartos> informativos = new ArrayList<InformativoDeQuartos>();
		for (Quarto quarto : quartosDisponiveis){
			Double valorDaDiaria = calculoValorDiaria.calcularValorDaDiaria(reservaComTodosOsQuartos, quarto);
			InformativoDeQuartos info = new InformativoDeQuartos(quarto.getId(), quarto.getNumero(), quarto.getDescricao(), quarto.getObservacao(), valorDaDiaria, quarto.getCategoria().getDescricao(), reservaComTodosOsQuartos.getInicio(), reservaComTodosOsQuartos.getFim());
			informativos.add(info);
		}
		return informativos;
	}

}

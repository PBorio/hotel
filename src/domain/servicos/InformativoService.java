package domain.servicos;

import java.util.List;

import domain.PoliticaDePrecos;
import domain.Quarto;

public class InformativoService {

	private List<PoliticaDePrecos> politicas;

	public InformativoService(List<PoliticaDePrecos> politicas) {
		this.politicas = politicas;
	}

	public List<InformativoDeQuartos> criarInformativoDeQuartos(List<Quarto> quartosDisponiveis) {
		CalculoDeValorDaDiariaService calculoValorDiaria = new CalculoDeValorDaDiariaService(politicas).calcularEInformarValorNaReserva(reserva);
		for (Quarto quarto : quartosDisponiveis){
			
		}
		return null;
	}

}

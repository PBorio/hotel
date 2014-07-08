package controllers.views.reservas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Quarto;

public class ParametrosReserva {
	
	
	private List<DetalhesDosParametros> detalhes = new ArrayList<DetalhesDosParametros>();
	
	private Date chegada;
	
	private Date saida;
	
	private Integer numeroDeQuartos;


	public Date getChegada() {
		return chegada;
	}

	public void setChegada(Date chegada) {
		this.chegada = chegada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Integer getNumeroDeQuartos() {
		return numeroDeQuartos;
	}

	public void setNumeroDeQuartos(Integer numeroDeQuartos) {
		this.numeroDeQuartos = numeroDeQuartos;
	}

	public List<DetalhesDosParametros> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<DetalhesDosParametros> detalhes) {
		this.detalhes = detalhes;
	}

	public DetalhesDosParametros primeiroDetalheSemQuarto() {
		for (DetalhesDosParametros detalhe : this.detalhes){
			if (detalhe.getQuarto() == null)
				return detalhe;
		}
		return null;
	}

	public List<Quarto> getQuartos() {
		List<Quarto> quartos = new ArrayList<Quarto>();
		for (DetalhesDosParametros d : this.detalhes){
			if (d.getQuarto() != null)
				quartos.add(d.getQuarto());
		}
		return quartos;
	}

	public Integer getNumeroDeQuartosJaSelecionados() {
		Integer numeroDeQuartoSelecionados = 0;
		
		for (DetalhesDosParametros d : detalhes){
			if (d.getQuarto() != null) 
				numeroDeQuartoSelecionados++;
		}
		return numeroDeQuartoSelecionados;
	}

	public boolean jaTemOQuarto(Quarto quarto) {
		for (DetalhesDosParametros d : detalhes){
			if (quarto.equals(d.getQuarto())) 
				return true;
		}
		return false;
	}

}

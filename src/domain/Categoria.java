package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import domain.servicos.helpers.Arredondamento;

@Entity
@Table(name="categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private String observacao;
	
	private Double valor;
	
	private Double valorACada2CriancasDe0a5;
	
	private Double valorCadaCrianca0a16;
	
	private Double valorCadaAdultoExtra;
	
	private Double valorParaUmAdulto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Categoria))
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorACada2CriancasDe0a5() {
		return valorACada2CriancasDe0a5;
	}

	public void setValorACada2CriancasDe0a5(Double valorACada2CriancasDe0a5) {
		this.valorACada2CriancasDe0a5 = valorACada2CriancasDe0a5;
	}

	public Double getValorCadaCrianca0a16() {
		return valorCadaCrianca0a16;
	}

	public void setValorCadaCrianca0a16(Double valorCadaCrianca0a16) {
		this.valorCadaCrianca0a16 = valorCadaCrianca0a16;
	}

	public Double getValorCadaAdultoExtra() {
		return valorCadaAdultoExtra;
	}

	public void setValorCadaAdultoExtra(Double valorCadaAdultoExtra) {
		this.valorCadaAdultoExtra = valorCadaAdultoExtra;
	}

	public Double getValorParaUmAdulto() {
		return valorParaUmAdulto;
	}

	public void setValorParaUmAdulto(Double valorParaUmAdulto) {
		this.valorParaUmAdulto = valorParaUmAdulto;
	}

	public Double valorParaAReserva(Reserva reserva) {
		if (reserva.isSoParaUmAdulto()){
			if (this.valorParaUmAdulto == null)
				return this.valor;
			return this.valorParaUmAdulto;
		}
		
		Double valorDiaria = this.valor;
		
		valorDiaria += valorPorAdultoExtra(reserva);
		valorDiaria += valorParaCriancasDe0a5(reserva);
		valorDiaria += valorParaCriancasDe6a16(reserva);
		return new Arredondamento().arredondar(valorDiaria);
	}
	
	private Double valorParaCriancasDe6a16(Reserva reserva) {

		if (this.valorCadaCrianca0a16 == null || reserva.getNumeroCriancas6a16() == null)
			return 0.0;
		
		return (valorCadaCrianca0a16 * reserva.getNumeroCriancas6a16());
	}

	private Double valorParaCriancasDe0a5(Reserva reserva) {
		if (this.valorACada2CriancasDe0a5 == null)
			return 0.0;
		
		return (this.valorACada2CriancasDe0a5 * reserva.getParDeCriancasDe0a5());
	}

	private Double valorPorAdultoExtra(Reserva reserva) {
		
		if (this.valorCadaAdultoExtra == null)
			return 0.0;
		
		Integer numeroDeAdultoExtra = reserva.getNumeroAdultos() - 2;
		
		if (numeroDeAdultoExtra > 0)
			return (numeroDeAdultoExtra * this.valorCadaAdultoExtra);
		return 0.0;
	}


}

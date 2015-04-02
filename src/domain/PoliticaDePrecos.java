package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import domain.servicos.helpers.Arredondamento;

@Entity
@Table(name="politica_precos")
public class PoliticaDePrecos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@Column(name="inicio")
	private Date inicio;
	
	@Column(name="fim")
	private Date fim;
	
	@Column(name="valor_diaria")
	private Double valorDiaria;
	
	private Double valorACada2CriancasDe0a5;
	
	private Double valorCadaCrianca0a16;
	
	private Double valorCadaAdultoExtra;
	
	private Double valorParaUmAdulto;
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Date getFim() {
		return fim;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setValorDiaria(Double valor) {
		this.valorDiaria = valor;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean possuiAMesmaCategoria(PoliticaDePrecos outraPolitica) {
		if (this.categoria == null || outraPolitica.categoria == null)
			return false;
		
		return (this.categoria.equals(outraPolitica.categoria));
	}

	@Override
	public int hashCode() {
		if (id == null) return super.hashCode();
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
		if (getClass() != obj.getClass())
			return false;
		PoliticaDePrecos other = (PoliticaDePrecos) obj;
		if (id == null) 
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
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
				return this.valorDiaria;
			return this.valorParaUmAdulto;
		}
		
		Double valorDiaria = this.valorDiaria;
		
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

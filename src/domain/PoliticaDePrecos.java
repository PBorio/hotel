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


}

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
	
	@Column(name="padrao")
	private boolean padrao = false;

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

	public void setPadrao(boolean padrao) {
		this.padrao  = padrao;
	}

	public boolean isPadrao() {
		return padrao;
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


}

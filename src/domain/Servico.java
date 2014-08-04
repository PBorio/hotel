package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicos")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	
	@Column(name="valor_sugerido")
	private Double valorSugerido;
	private String observacao;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValorSugerido(Double valorSugerido) {
		this.valorSugerido = valorSugerido;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValorSugerido() {
		return valorSugerido;
	}

	public String getObservacao() {
		return observacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

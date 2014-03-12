package domain;

public class ServicoPrestado {

	private Long id;
	
	private Double valor;
	private Servico servico;
	private String observacao;

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return this.valor;
	}

	public void paraOServico(Servico servico) {
		this.servico = servico;
		this.valor = servico.getValorSugerido();
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (!(obj instanceof ServicoPrestado))
			return false;
		ServicoPrestado other = (ServicoPrestado) obj;
		if (id == null)
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

}

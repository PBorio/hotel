package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pagamentos_estadias")
public class PagamentoEstadia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="estadia_id")
	private Estadia estadia;
	
	@ManyToOne
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;
	
	public PagamentoEstadia(){}
	

	public PagamentoEstadia(Estadia estadia, Pagamento pagamento) {
		this.estadia = estadia;
		this.pagamento = pagamento;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estadia getEstadia() {
		return estadia;
	}

	public void setEstadia(Estadia estadia) {
		this.estadia = estadia;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}

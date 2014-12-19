package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="pagamentos_reservas")
public class PagamentoReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="reserva_id")
	private Reserva reserva;
	
	@ManyToOne
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;

	private Double valor;

	public PagamentoReserva() {	}
	
	public PagamentoReserva(Reserva reserva, Pagamento pagamento){
		this.reserva = reserva;
		this.pagamento = pagamento;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setValor(Double valor) {
		this.valor= valor;
	}

	public Double getValor() {
		if (this.valor != null)
			return valor;
		
		return this.pagamento.getValor();
	}

}

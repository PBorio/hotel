package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import domain.servicos.helpers.CalculoDeProporcaoDePagamentoDasReservas;

@Entity
@Table(name="agrupador_reservas")
public class AgrupadorReservas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy="agrupadorReservas")
	private List<Reserva> reservas = new ArrayList<Reserva>();

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
		reserva.setAgrupador(this);
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void addPagamento(Pagamento pagamento) {
		CalculoDeProporcaoDePagamentoDasReservas calculo = new CalculoDeProporcaoDePagamentoDasReservas();
		calculo.dividirProporcionalmente(pagamento, reservas);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hospede getResponsavel(){
		if (reservas.size() == 0)
			return null;
		
		return reservas.get(0).getHospede();
	}
}

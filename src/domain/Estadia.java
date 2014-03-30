package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import domain.exceptions.DataFechamentoNaoInformadoException;
import domain.interfaces.CalculavelPorPeriodo;
import domain.nulos.ReservaNulo;
import domain.servicos.CalculoDeValorPorPeriodoService;

@Entity
@Table(name="estadias")
public class Estadia implements CalculavelPorPeriodo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="estadia", cascade=CascadeType.ALL)
	private Set<HospedeDaEstadia> hospedes = new HashSet<HospedeDaEstadia>();
	
	@Transient
	private Set<ServicoPrestado> servicosPrestados = new HashSet<ServicoPrestado>();
	
	@Transient
	private Set<Consumo> consumos = new HashSet<Consumo>();
	
	@ManyToOne
	@JoinColumn(name="reserva_id")
	private Reserva reserva = new ReservaNulo();
	
	@ManyToOne
	@JoinColumn(name="quarto_id")
	private Quarto quarto;
	
	@Column(name="data_checkin")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCheckin;
	
	@Column(name="previsao_checkout")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime previsaoCheckout;
	
	@Column(name="data_checkout")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCheckout;
	
	@Column(name="data_cancelamento")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCancelamento;
	
	@Column(name="valor_diaria")
	private Double valorDiaria;
	
	

	public void aPartirDaReserva(Reserva reserva) {
		this.reserva = reserva;
		this.addHospede(reserva.getHospede());
		this.quarto = reserva.getQuarto();
		this.dataCheckin = reserva.getInicio();
		this.previsaoCheckout = reserva.getFim();
		this.valorDiaria = reserva.getValorDiaria();
	}

	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva){
		this.reserva = reserva;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public DateTime getDataCheckin() {
		return dataCheckin;
	}

	public DateTime getPrevisaoCheckout() {
		return previsaoCheckout;
	}

	public DateTime getDataCheckout() {
		return dataCheckout;
	}

	public List<Hospede> getHospedes() {
		
		List<Hospede> result = new ArrayList<Hospede>();
		
		for (HospedeDaEstadia h : hospedes){
			result.add(h.getHospede());
		}
		return result;
	}

	public void setDataCheckin(DateTime dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public void addHospede(Hospede hospede) {
		HospedeDaEstadia hospedeEstadia = new HospedeDaEstadia(this,hospede);
		this.hospedes.add(hospedeEstadia);
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public boolean isAberta() {
		return (!isFechada() && !isCancelada());
	}
	
	public boolean isFechada() {
		return (!isCancelada() && this.dataCheckout != null);
	}

	public boolean isCancelada() {
		return (this.dataCancelamento != null);
	}

	public void fechar(DateTime checkout) {
		this.dataCheckout = checkout;
	}

	public void cancelar(DateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Double getValorDiaria() {
		return this.valorDiaria;
	}
	
	public DateTime getInicio() {
		return this.dataCheckin;
	}

	public DateTime getFim() {
		return this.dataCheckout;
	}

	public Double valorAteAData(DateTime data) {
		
		if (isCancelada())
			return 0.0;
		
		if (isFechada())
			data = dataCheckout;
			
		return new CalculoDeValorPorPeriodoService().calcularValorAteAData(this, data);
	}

	public Double getValorDosServicos() {
		Double result = 0.0;
		for (ServicoPrestado s : servicosPrestados){
			result += s.getValor();
		}
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public Double getValorConsumido() {
		Double result = 0.0;
		for (Consumo consumo : consumos){
			result += consumo.getValor();
		}
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public void addServicoPrestado(ServicoPrestado servicoPrestado) {
		this.servicosPrestados.add(servicoPrestado);
	}

	public void addConsumo(Consumo consumo) {
		this.consumos.add(consumo);
	}
	
	public List<Consumo> getConsumos(){
		return new ArrayList<Consumo>(consumos);
	}

	public Double getValorDaEstadiaFechada() {
		
		if (dataCheckout == null)
			throw new DataFechamentoNaoInformadoException("Estadia em aberto");
		
		Double result = valorAteAData(this.dataCheckout)+getValorDosServicos()+getValorConsumido();
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public Double getValorDaEstadiaNaData(DateTime data) {
		
		if (dataCheckout != null)
			data = dataCheckout;
		
		Double result = valorAteAData(data)+getValorDosServicos()+getValorConsumido();
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}

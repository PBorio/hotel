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
	private Set<HospedeDaEstadia> hospedesDaEstadia = new HashSet<HospedeDaEstadia>();
	
	@OneToMany(mappedBy="estadia")
	private Set<ServicoPrestado> servicosPrestados = new HashSet<ServicoPrestado>();
	
	@OneToMany(mappedBy="estadia")
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

	@OneToMany(mappedBy="estadia")
	private List<PagamentoEstadia> pagamentosEstadias = new ArrayList<PagamentoEstadia>();
	
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
	
	public void setPrevisaoCheckout(DateTime previsaoCheckout) {
		this.previsaoCheckout = previsaoCheckout;
	}

	public DateTime getDataCheckout() {
		return dataCheckout;
	}

	public void setDataCheckin(DateTime dataCheckin) {
		this.dataCheckin = dataCheckin;
	}

	public void addHospede(Hospede hospede) {
		HospedeDaEstadia hospedeEstadia = new HospedeDaEstadia(this,hospede);
		this.hospedesDaEstadia.add(hospedeEstadia);
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
	
	public List<ServicoPrestado> getServicosPrestados(){
		return new ArrayList<ServicoPrestado>(servicosPrestados);
	}

	public void addConsumo(Consumo consumo) {
		this.consumos.add(consumo);
	}
	
	public List<Consumo> getConsumos(){
		return new ArrayList<Consumo>(consumos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Hospede> getHospedes() {
		
		List<Hospede> result = new ArrayList<Hospede>();
		
		for (HospedeDaEstadia h : hospedesDaEstadia){
			result.add(h.getHospede());
		}
		return result;
	}

	public Set<HospedeDaEstadia> getHospedesDaEstadia() {
		return hospedesDaEstadia;
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
		Estadia other = (Estadia) obj;
		if (id == null) 
				return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

	public void addPagamento(Pagamento pagamento) {
		PagamentoEstadia pagamentoEstadia = new PagamentoEstadia(this, pagamento);
		this.pagamentosEstadias  .add(pagamentoEstadia);
	}

	public List<PagamentoEstadia> getPagamentosEstadias() {
		return pagamentosEstadias;
	}

	public Double getValorPago() {
		Double result = 0.0;
		if (this.reserva != null)
			result = reserva.getValorPago();
		
		for (PagamentoEstadia pe : this.pagamentosEstadias){
			if (pe.getPagamento().getDataPagamento() != null)
				result += pe.getPagamento().getValor();
		}
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
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
	
	public Double getPrevisaoDoValorFinal() {
		if (previsaoCheckout == null)
			throw new DataFechamentoNaoInformadoException("Estadia sem Previsão de checkout.");
		
		Double result = valorAteAData(this.previsaoCheckout)+getValorDosServicos()+getValorConsumido();
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public Double getSaldoAPagar() {
		DateTime data = null;
		
		if (previsaoCheckout != null)
			data = previsaoCheckout;
			
		if (dataCheckout != null)
			data = dataCheckout;
		
		Double result = (valorAteAData(data)+getValorDosServicos()+getValorConsumido()) - getValorPago();
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

}

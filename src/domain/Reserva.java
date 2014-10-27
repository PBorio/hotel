package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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
import org.joda.time.Interval;

import domain.interfaces.CalculavelPorPeriodo;
import domain.servicos.CalculoDeValorPorPeriodoService;
import domain.servicos.helpers.Periodo;

@Entity
@Table(name="reservas")
public class Reserva implements CalculavelPorPeriodo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="inicio")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime inicio;
	
	@Column(name="fim")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime fim;
	
	@Column(name="numero_adultos")
	private Integer numeroAdultos;
	
	@Column(name="numero_criancas_05")
	private Integer numeroCriancas0a5;
	
	@Column(name="numero_criancas_6_16")
	private Integer numeroCriancas6a16;
	
	@Column(name="numero_criancas_17_18")
	private Integer numeroCriancas17a18;
	
	@ManyToOne
	@JoinColumn(name="hospede_id")
	private Hospede hospede;

	@ManyToOne
	@JoinColumn(name="quarto_id")
	private Quarto quarto;
	
	@Column(name="checkin")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime checkin;
	
	@Column(name="cancelamento")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime cancelamento;

	@Column(name="valor_diaria")
	private Double valorDiaria;

	@OneToMany(mappedBy="reserva")
	private List<PagamentoReserva> pagamentosReservas = new ArrayList<PagamentoReserva>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorDiaria() {
		return this.valorDiaria;
	}

	public void setValorDiaria(Double valor) {
		this.valorDiaria = valor;
	}
	
	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public void setFim(DateTime fim) {
		this.fim = fim;
	}
	
	public DateTime getInicio() {
		return inicio;
	}
	
	public DateTime getFim() {
		return fim;
	}

	public void setNumeroAdultos(Integer numeroAdultos) {
		this.numeroAdultos = numeroAdultos;
	}

	public void setNumeroCriancas0a5(Integer numeroCriancas0a5) {
		this.numeroCriancas0a5 = numeroCriancas0a5;
	}

	public void setNumeroCriancas6a16(Integer numeroCriancas6a16) {
		this.numeroCriancas6a16 = numeroCriancas6a16;
	}

	public Integer getNumeroAdultos() {
		return numeroAdultos;
	}

	public Integer getNumeroCriancas0a5() {
		return numeroCriancas0a5;
	}

	public Integer getNumeroCriancas6a16() {
		return numeroCriancas6a16;
	}

	public void setNumeroCriancas17a18(Integer numeroCriacas17a18) {
		this.numeroCriancas17a18 = numeroCriacas17a18;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public DateTime getCheckin() {
		return checkin;
	}

	public void setCheckin(DateTime checkin) {
		this.checkin = checkin;
	}

	public DateTime getCancelamento() {
		return cancelamento;
	}

	public void setCancelamento(DateTime cancelamento) {
		this.cancelamento = cancelamento;
	}
	
	public boolean contemAData(DateTime dia) {
		
		dia = dia.withTimeAtStartOfDay();
		if (dia.equals(inicio.withTimeAtStartOfDay()) || dia.equals(fim.withTimeAtStartOfDay()))
			return true;
		
		return new Interval(inicio, fim).contains(dia);
	}

	public boolean coincideCom(Reserva outraReserva) {
		Periodo periodoDestaReserva = new Periodo(inicio, fim);
		Periodo periodoDaOutraReserva = new Periodo(outraReserva.getInicio(), outraReserva.getFim());
 		return periodoDestaReserva.coincideCom(periodoDaOutraReserva);
	}

	public Double getValorReserva() {
		Double valor = new CalculoDeValorPorPeriodoService().calcularValor(this);
		BigDecimal bd = new BigDecimal(valor.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public Double getValorPago() {
		Double result = 0.0;
		for (PagamentoReserva pr : this.pagamentosReservas){
			Pagamento pg = pr.getPagamento();
			if (pg.getDataPagamento() != null)
				result += pg.getValorPagamento();
		}
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public Integer getNumeroCriancas17a18() {
		return numeroCriancas17a18;
	}

	public Quarto getQuarto(){
		return this.quarto;
	}

	public void setQuarto(Quarto quarto) {
		quarto.addReserva(this);
		this.quarto = quarto;
	}

	public boolean isSoParaUmAdulto() {
		if (this.numeroAdultos == null)
			return false;
		
		if (this.numeroCriancas0a5 != null && this.numeroCriancas0a5.intValue()  > 0)
			return false;
		
		if (this.numeroCriancas6a16 != null && this.numeroCriancas6a16.intValue() > 0)
			return false;
		
		if (this.numeroCriancas17a18 != null && this.numeroCriancas17a18.intValue() > 0)
			return false;
		
		return (numeroAdultos.intValue() == 1);
	}

	public int getParDeCriancasDe0a5() {
		if (numeroCriancas0a5 == null)
			return 0;
		return (this.numeroCriancas0a5 / 2);
	}

	public void addPagamento(Pagamento pagamento) {
		this.pagamentosReservas.add(new PagamentoReserva(this, pagamento));
	}

	public boolean isPossuiPagamento() {
		for (PagamentoReserva pr : this.pagamentosReservas){
			Pagamento pagamento = pr.getPagamento(); 
			if (pagamento.getDataPagamento() != null)
				return true;
		}
		return false;
	}
	
	public boolean isPossuiPagamentoOuPrevisao() {
		return this.pagamentosReservas.size() > 0;
	}

	public Double getSaldoAPagar() {
		Double valorReserva = this.getValorReserva();
		Double valorPago = this.getValorPago();
		
		Double result = valorReserva - valorPago;
		BigDecimal bd = new BigDecimal(result.toString());
	  	return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	public List<Pagamento> getPagamentos() {
		List<Pagamento> pagamentos = new ArrayList<Pagamento>();
		for (PagamentoReserva pr : this.pagamentosReservas){
			pagamentos.add(pr.getPagamento());
		}
		return pagamentos;
	}
	
	public List<PagamentoReserva> getPagamentosReservas(){
		return this.pagamentosReservas;
	}
	
	public void cancelar() {
		this.cancelamento = new DateTime();
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
		Reserva other = (Reserva) obj;
		if (id == null)
			return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}

}

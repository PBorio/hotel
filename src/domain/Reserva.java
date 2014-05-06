package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@OneToMany(mappedBy="reserva")
	private Set<QuartoDaReserva> quartosDaReserva = new HashSet<QuartoDaReserva>();
	
	@Column(name="checkin")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime checkin;
	
	@Column(name="cancelamento")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime cancelamento;

	@Column(name="valor_diaria")
	private Double valorDiaria;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Quarto getQuarto() {
//		return quarto;
//	}
//
//	public void setQuarto(Quarto quarto) {
//		this.quarto = quarto;
//	}

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

	public Integer getNumeroCriacas17a18() {
		return numeroCriancas17a18;
	}

	public void setNumeroCriacas17a18(Integer numeroCriacas17a18) {
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
		
		dia = dia.withTime(0, 0, 0, 0);
		if (dia.equals(inicio.withTime(0, 0, 0, 0)) || dia.equals(fim.withTime(0, 0, 0, 0)))
			return true;
		
		return new Interval(inicio, fim).contains(dia);
	}

	public boolean coincideCom(Reserva outraReserva) {
		Periodo periodoDestaReserva = new Periodo(inicio, fim);
		Periodo periodoDaOutraReserva = new Periodo(outraReserva.getInicio(), outraReserva.getFim());
		return periodoDestaReserva.coincideCom(periodoDaOutraReserva);
	}

	public Double getValorReserva() {
		return new CalculoDeValorPorPeriodoService().calcularValor(this);
	}

	public void addQuarto(Quarto quarto) {
		QuartoDaReserva quartoDaReserva = new QuartoDaReserva(this, quarto);
		quarto.addReserva(quartoDaReserva);
		this.quartosDaReserva.add(quartoDaReserva);
	}

	public List<Quarto> getQuartos() {
		List<Quarto> result = new ArrayList<Quarto>();
		for (QuartoDaReserva quartoReserva : this.quartosDaReserva){
			result.add(quartoReserva.getQuarto());
		}
		return result;
	}

}

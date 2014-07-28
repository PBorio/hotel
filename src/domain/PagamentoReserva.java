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
import javax.persistence.Transient;

import domain.servicos.tipos.TipoPagamento;

@Entity
@Table(name="pagamentos_reservas")
public class PagamentoReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer tipoPagamento;
	
	@Column(name="data_previsao")
	private Date dataPrevisao;
	
	private String banco;

	@Column(name="data_pagamento")
	private Date dataPagamento;
	
	@Column(name="numero_cartao")
	private String numeroCartao;
	
	@Transient
	private Date dataDeposito;
	
	private Double valor;
	
	@Transient
	private Double valorDeposito;
	
	@ManyToOne
	@JoinColumn(name="reserva_id")
	private Reserva reserva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Date getDataDeposito() {
		if (dataDeposito == null)
			return dataDeposito;
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	
	public Double getValorDeposito() {
		if (valorDeposito == null)
			return valor;
		return valorDeposito;
	}

	public void setValorDeposito(Double valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public Double getValorPagamento() {
		return this.valor;
	}

	public void arrumaValores() {
		if (this.tipoPagamento.equals(TipoPagamento.DEPOSITO)){
			this.valor = valorDeposito;
			this.dataPagamento = dataDeposito;
		}
	}
	
	public String getDescricaoDoTipo(){
		if (tipoPagamento == null)
			return "";
		
		if (tipoPagamento.equals(1))
			return "Cartão";
		
		if (tipoPagamento.equals(2))
			return "Depósito";
		
		return "";
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
}

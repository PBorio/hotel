package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import domain.exceptions.HotelException;
import domain.servicos.tipos.TipoPagamento;

@Entity
@Table(name="pagamentos")
public class Pagamento {
	
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
	
	@OneToMany(mappedBy="pagamento")
	public List<PagamentoReserva> pagamentoReservas = new ArrayList<PagamentoReserva>();
	
	@OneToMany(mappedBy="pagamento")
	public List<PagamentoEstadia> pagamentoEstadias = new ArrayList<PagamentoEstadia>();
	
	@Transient
	private Date dataDeposito;
	
	private Double valor;
	
	@Transient
	private Double valorDeposito;
	
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
		if (this.pagamentoReservas.isEmpty())
			return null;
		return this.pagamentoReservas.get(0).getReserva();
	}
	
	public void setReserva(Reserva reserva){
		if (!this.pagamentoReservas.isEmpty())
			throw new HotelException("Pagamento já possui informação de Reserva. Erro Interno.");
		
		PagamentoReserva pagamento = new PagamentoReserva(reserva, this);
		this.pagamentoReservas.add(pagamento);
	}
	
	public Estadia getEstadia() {
		if (this.pagamentoEstadias.isEmpty())
			return null;
		return this.pagamentoEstadias.get(0).getEstadia();
	}
	
	public void setEstadia(Estadia estadia){
		if (!this.pagamentoEstadias.isEmpty())
			throw new HotelException("Pagamento já possui informação de Reserva. Erro Interno.");
		
		PagamentoEstadia pagamento = new PagamentoEstadia(estadia, this);
		this.pagamentoEstadias.add(pagamento);
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
		if (this.tipoPagamento.equals(TipoPagamento.DEPOSITO.getValue())||
			this.tipoPagamento.equals(TipoPagamento.DINHEIRO.getValue())){
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
		
		if (tipoPagamento.equals(3))
			return "Dinheiro";
		
		return "";
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public List<PagamentoReserva> getPagamentoReservas() {
		return pagamentoReservas;
	}

	public boolean foiMarcado() {
		return (TipoPagamento.DEPOSITO.getValue().equals(this.tipoPagamento) ||
				TipoPagamento.CARTAO.getValue().equals(this.tipoPagamento) ||
				TipoPagamento.DINHEIRO.getValue().equals(this.tipoPagamento));
	}
}

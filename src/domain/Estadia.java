package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import domain.exceptions.DataFechamentoNaoInformadoException;
import domain.interfaces.CalculavelPorPeriodo;
import domain.nulos.ReservaNulo;
import domain.servicos.CalculoDeValorPorPeriodoService;

public class Estadia implements CalculavelPorPeriodo {

	
	private Long id;
	
	private Set<HospedeDaEstadia> hospedes = new HashSet<HospedeDaEstadia>();
	private Set<ServicoPrestado> servicosPrestados = new HashSet<ServicoPrestado>();
	private Set<Consumo> consumos = new HashSet<Consumo>();
	
	private Reserva reserva = new ReservaNulo();
	private Quarto quarto;
	private DateTime dataCheckin;
	private DateTime previsaoCheckout;
	private DateTime dataCheckout;
	private DateTime dataCancelamento;
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

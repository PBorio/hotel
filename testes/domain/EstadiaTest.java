package domain;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.exceptions.DataFechamentoNaoInformadoException;
import domain.helpers.FakeConsumo;
import domain.helpers.FakeReserva;
import domain.servicos.Checkin;
import domain.servicos.helpers.ParserDeStringParaData;
import domain.servicos.tipos.TipoPagamento;

public class EstadiaTest {
	
	
	@Test
	public void umaEstadiaEstaAbertaSeNaoTiverDataDeCheckout(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		Assert.assertTrue(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaEstaFechadaSeTiverDataDeCheckout(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		Assert.assertFalse(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaEstaNaoEstaAbertaSeEstiverCancelada(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.cancelar(new ParserDeStringParaData().parseData("09/03/2014"));
		Assert.assertFalse(estadia.isAberta());
	}
	
	@Test
	public void umaEstadiaNaoEstaFechadaSeTiverDataDeCheckoutMasTambemDeCancelamento(){
		Estadia estadia = new Estadia();
		estadia.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014"));
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		estadia.cancelar(new ParserDeStringParaData().parseData("11/03/2014"));
		Assert.assertFalse(estadia.isFechada());
		Assert.assertTrue(estadia.isCancelada());
	}
	
	@Test
	public void oValorDeUmaEstadiaEmAbertoComReservaEhOValorDaDiariaDaReservaAteADataInformada(){
		Reserva reserva = new FakeReserva().iniciandoEm("09/03/2014").terminandoEm("11/03/2014").paraOHospede("Joao").noQuarto("19").build();
		reserva.setValorDiaria(10.0);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		DateTime hoje = new ParserDeStringParaData().parseData("11/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}
	
	@Test
	public void casoAEstadiaNaoTenhaSidoCriadaAtravesDeUmaReservaElaMesmaTeraQueInformarOValorDaDiaria(){
		
		Checkin checkin = new Checkin();
		checkin.setQuarto(new Quarto());
		checkin.addHospede(new Hospede());
		checkin.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014").toDate());
		checkin.setValorDiaria(10.0);
		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		
		DateTime hoje = new ParserDeStringParaData().parseData("11/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}
	
	@Test
	public void casoAEstadiaJaEstejaFechadaOValorCalculadoEhSomenteAteADataDeFechamento(){
		
		Checkin checkin = new Checkin();
		checkin.setQuarto(new Quarto());
		checkin.addHospede(new Hospede());
		checkin.setDataCheckin(new ParserDeStringParaData().parseData("09/03/2014").toDate());
		checkin.setValorDiaria(10.0);

		Estadia estadia = checkin.iniciarEstadiaSemReserva();
		estadia.fechar(new ParserDeStringParaData().parseData("11/03/2014"));
		
		DateTime hoje = new ParserDeStringParaData().parseData("14/03/2014");
		Assert.assertEquals((Double)20.0, estadia.valorAteAData(hoje));
	}

	@Test
	public void oValorDosServicosDaEstadiaEhASomaDosValoresDosServicosPrestados(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setValor(10.0);
		
		estadia.addServicoPrestado(servicoPrestado);
		
		Assert.assertEquals((Double)10.0, estadia.getValorDosServicos());
	}
	
	@Test
	public void tendoUmServicoDe10EUmDe5OTotalDosServicosSera15(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
    	Estadia estadia= checkin.iniciarEstadiaAPartirDeUmaReserva();
		 
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setValor(10.0);
		
		ServicoPrestado outroServico = new ServicoPrestado();
		outroServico.setValor(5.0);
		
		estadia.addServicoPrestado(servicoPrestado);
		estadia.addServicoPrestado(outroServico);
		
		Assert.assertEquals((Double)15.0, estadia.getValorDosServicos());
	}
	
	@Test
	public void seAEstadiaNaoUtilizouServicosOValorDosServicosEhZero(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Assert.assertEquals((Double)0.0, estadia.getValorDosServicos());
	}
	
	@Test
	public void oValorDoConsumoEhASomaDosProdutosConsumidosDuranteAEstadia(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(25.0).comQuantidade(1).build();
		
		estadia.addConsumo(consumo);
		
		Assert.assertEquals((Double)25.0, estadia.getValorConsumido());
	}
	
	@Test
	public void oValorConsumidoParaUmProdutoDe30EOutroDe10Eh40(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		Consumo outroConsumo = new FakeConsumo("Lanche").comValorDe(10.0).comQuantidade(1).build();
		
		estadia.addConsumo(consumo);
		estadia.addConsumo(outroConsumo);
		
		Assert.assertEquals((Double)40.0, estadia.getValorConsumido());
	}
	
	@Test
	public void oValorConsumidoParaDeveConsiderarAQuantidadeEOPreco(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("19/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(2).build();
		
		estadia.addConsumo(consumo);
		
		Assert.assertEquals((Double)60.0, estadia.getValorConsumido());
	}
	
	@Test
	public void oValorTotalDaEstadiaEhASomaDoValorDaEstadiaDoValorDosServicosEDoConsumo(){
		
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setValor(10.0);
		ServicoPrestado outroServico = new ServicoPrestado();
		outroServico.setValor(10.0);
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		Consumo outroConsumo = new FakeConsumo("Lanche").comValorDe(10.0).comQuantidade(1).build();
		
		estadia.addServicoPrestado(servicoPrestado);
		estadia.addServicoPrestado(outroServico);
		
		estadia.addConsumo(consumo);
		estadia.addConsumo(outroConsumo);
		
		estadia.fechar(new ParserDeStringParaData().parseData("14/03/2014"));
		
		Assert.assertEquals((Double)80.0, estadia.getValorDaEstadiaFechada());
		
	}
	
	@Test(expected=DataFechamentoNaoInformadoException.class)
	public void seAEstadiaTiverAbertaNaoPodeConsultarOValorFechado(){
		
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		estadia.getValorDaEstadiaFechada();
	}
	
	
	@Test
	public void podeSerConsultadoOValorDaEstadiaEmAbertoEmDeterminadaData(){
		
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setValor(10.0);
		ServicoPrestado outroServico = new ServicoPrestado();
		outroServico.setValor(10.0);
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		Consumo outroConsumo = new FakeConsumo("Lanche").comValorDe(10.0).comQuantidade(1).build();
		
		estadia.addServicoPrestado(servicoPrestado);
		estadia.addServicoPrestado(outroServico);
		
		estadia.addConsumo(consumo);
		estadia.addConsumo(outroConsumo);
		
		DateTime hoje = new ParserDeStringParaData().parseData("15/03/2014");
		
		Assert.assertEquals((Double)90.0, estadia.getValorDaEstadiaNaData(hoje));
	}
	
	@Test
	public void oValorPagoEhOValorPagoDaReservaMaisOValorPagoDaEstadia(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Pagamento pagamentoNaEstadia = new Pagamento();
		pagamentoNaEstadia.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamentoNaEstadia.setValor(5.0);
		estadia.addPagamento(pagamento);
		
		Assert.assertEquals((Double)10.0, estadia.getValorPago());
	}
	
	@Test
	public void oValorFinalDeUmaEstadiaEhOValorDaEstadiaMenosOValorPago(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		estadia.addConsumo(consumo);
		
		Assert.assertEquals((Double)50.0, estadia.getPrevisaoDoValorFinal());
	}
	
	@Test
	public void oPagamentoSoEhConsideradoSeTiver(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Pagamento pagamentoNaEstadia = new Pagamento();
		pagamentoNaEstadia.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamentoNaEstadia.setValor(5.0);
		estadia.addPagamento(pagamento);
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		estadia.addConsumo(consumo);
		
		Assert.assertEquals((Double)40.0, estadia.getSaldoAPagar());
	}
	
	@Test
	public void oSaldoAPagarEhOValorDaEstadiaNoCheckoutMaisConsumoEServicoMenosOValorJaPago(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Pagamento pagamentoNaEstadia = new Pagamento();
		pagamentoNaEstadia.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamentoNaEstadia.setValor(5.0);
		estadia.addPagamento(pagamento);
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		estadia.addConsumo(consumo);
		
		estadia.fechar(new ParserDeStringParaData().parseData("15/03/2014"));
		
		Assert.assertEquals((Double)50.0, estadia.getSaldoAPagar());
	}
	
	@Test
	public void seAindaNaoFoiFeitoOCheckoutOSaldoAPagarDeveConsiderarAPrevisaoDeCheckout(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("12/03/2014").terminandoEm("14/03/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Pagamento pagamentoNaEstadia = new Pagamento();
		pagamentoNaEstadia.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamentoNaEstadia.setValor(5.0);
		estadia.addPagamento(pagamento);
		
		Consumo consumo = new FakeConsumo("Bebida").comValorDe(30.0).comQuantidade(1).build();
		estadia.addConsumo(consumo);
		
		Assert.assertEquals((Double)40.0, estadia.getSaldoAPagar());
	}
	
	@Test
	public void aEstadiaNaoEstaPendenteSeOSaldoAPagarForZero(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("07/08/2014").terminandoEm("08/08/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Pagamento pagamentoNaEstadia = new Pagamento();
		pagamentoNaEstadia.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamentoNaEstadia.setValor(5.0);
		estadia.addPagamento(pagamento);
		
		Assert.assertFalse(estadia.isPendente());
	}
	
	@Test
	public void aEstadiaEstaPendenteSeOSaldoAPagarForMaiorQueZero(){
		Reserva reserva = 
				new FakeReserva().iniciandoEm("07/08/2014").terminandoEm("08/08/2014").paraOHospede("Joao").noQuarto("1").comValorDaDiariaDe(10.0).build();
		
		Pagamento pagamento = new Pagamento();
		pagamento.setTipoPagamento(TipoPagamento.CARTAO.getValue());
		pagamento.setDataPagamento(new Date());
		pagamento.setValor(5.0);
		reserva.addPagamento(pagamento);
		
		Checkin checkin = new Checkin();
		checkin.aPartirDaReserva(reserva);
		checkin.addHospede(reserva.getHospede());
		Estadia estadia = checkin.iniciarEstadiaAPartirDeUmaReserva();
		
		Assert.assertTrue(estadia.isPendente());
	}
	
}

package domain.servicos;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import domain.Quarto;
import domain.Reserva;
import domain.helpers.FakeReserva;
import domain.servicos.helpers.ParserDeStringParaData;
import domain.servicos.tipos.TipoStatusQuarto;

public class StatusDeReservaNoDiaTest {
	
	private ParserDeStringParaData parser = new ParserDeStringParaData();
	private List<Quarto> quartos = new ArrayList<Quarto>();
	
	@Test
	public void oStatusDeReservaDeveIndicarQualOStatusParaCadaUmDosQuartos(){
		DateTime dia = parser.parseData("21/02/2014");
		
		Quarto _001 = new Quarto();
		_001.setId(1l);
		
		quartos.add(_001);
		
		StatusDeReservasNoDia statusReserva = new StatusDeReservasNoDia(dia, quartos);
		Assert.assertEquals(TipoStatusQuarto.LIVRE, statusReserva.getStatusDosQuartos().get(0));
	}
	
	
	@Test
	public void oStatusDeveIndicarQueUmQuartoEstaLivreEOutroReservado(){
		DateTime dia = parser.parseData("21/02/2014");
		
		Reserva reserva = new FakeReserva().iniciandoEm("15/02/2014").terminandoEm("21/02/2014").build();
		
		Quarto _001 = new Quarto();
		_001.setId(1l);
		
		Quarto _002 = new Quarto();
		_002.setId(2l);
		reserva.addQuarto(_002);
		
		quartos.add(_001);
		quartos.add(_002);
		
		StatusDeReservasNoDia statusReserva = new StatusDeReservasNoDia(dia, quartos);
		Assert.assertEquals(TipoStatusQuarto.LIVRE, statusReserva.getStatusDosQuartos().get(0));
		Assert.assertEquals(TipoStatusQuarto.RESERVADO, statusReserva.getStatusDosQuartos().get(1));
	}

}

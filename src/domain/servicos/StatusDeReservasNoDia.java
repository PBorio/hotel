package domain.servicos;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import domain.Quarto;
import domain.servicos.tipos.TipoStatusQuarto;


public class StatusDeReservasNoDia {

	private DateTime dia;
	private List<Quarto> quartos = new ArrayList<Quarto>();
	
	public StatusDeReservasNoDia(DateTime dia, List<Quarto> quartos) {
		this.dia = dia;
		this.quartos = quartos;
	}

	public List<StatusQuarto> getStatusQuartos(){
		List<StatusQuarto> result = new ArrayList<StatusQuarto>();
		for (Quarto quarto : quartos){
			result.add(new StatusQuarto(quarto,dia));
		}
		return result;
	}
	
	public List<TipoStatusQuarto> getStatusDosQuartos(){
		List<TipoStatusQuarto> result = new ArrayList<TipoStatusQuarto>();
		for (Quarto quarto : quartos){
			result.add(new StatusQuarto(quarto,dia).getTipoStatusNaData());
		}
		return result;
	}
	
	public List<Quarto> getQuartos(){
		return this.quartos;
	}
	
	public DateTime getDia(){
		return this.dia;
	}
	
	public boolean isFimDeSemana(){
		return (this.dia.getDayOfWeek() == 6 || this.dia.getDayOfWeek() == 7);
	}
	
	
}

package domain.servicos.helpers;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

public class ParserDeStringParaData {
	
	public DateTime parseData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTime primeiroDeMarco;
		try{
			primeiroDeMarco = new DateTime(sdf.parse(data).getTime());
		}catch(Exception e){
			throw new RuntimeException(e); 
		}
		return primeiroDeMarco;
	}

}

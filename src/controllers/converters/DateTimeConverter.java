package controllers.converters;

import java.util.ResourceBundle;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(DateTime.class)  
public class DateTimeConverter implements Converter<DateTime> {  
	public DateTime convert(String arg0, Class<? extends DateTime> arg1,
			ResourceBundle arg2) {
		// TODO Auto-generated method stub
		return null;
	}  
}

	

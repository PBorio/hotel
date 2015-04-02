package controllers.converters;

import java.util.Locale;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Convert(DateTime.class)  
@RequestScoped
public class DateTimeConverter implements Converter<DateTime> {


	public DateTime convert(String value, Class<? extends DateTime> type, ResourceBundle bundle) {
		if (value == null || "".equals(value.trim())) {
			return null;
		}
		
		try {
			return getFormatter().parseDateTime(value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected DateTimeFormatter getFormatter() {
		return DateTimeFormat.shortDate().withLocale(new Locale("pt","BR")); 
	}


}

	

package controllers.converters;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Convert(UploadedFile.class)
public class UploadedFileConverter implements Converter<UploadedFile> {
	
	private HttpServletRequest request;
	
	public UploadedFileConverter(HttpServletRequest request) {
	this.request = request;
	}

	public UploadedFile convert(String value, Class<? extends UploadedFile> type, ResourceBundle bundle) {
		return type.cast(request.getAttribute(value));
	}
	
}

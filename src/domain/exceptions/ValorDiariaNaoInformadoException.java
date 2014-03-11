package domain.exceptions;

public class ValorDiariaNaoInformadoException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorDiariaNaoInformadoException(String message) {
		super(message);
	}
	
	public ValorDiariaNaoInformadoException(Throwable e){
		super(e);
	}

}

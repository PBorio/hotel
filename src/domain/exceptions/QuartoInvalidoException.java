package domain.exceptions;

public class QuartoInvalidoException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuartoInvalidoException(String message) {
		super(message);
	}
	
	public QuartoInvalidoException(Throwable exception) {
		super(exception);
	}

}

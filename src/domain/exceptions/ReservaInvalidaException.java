package domain.exceptions;

public class ReservaInvalidaException extends HotelException {

	public ReservaInvalidaException(Throwable exception) {
		super(exception);
	}
	
	public ReservaInvalidaException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

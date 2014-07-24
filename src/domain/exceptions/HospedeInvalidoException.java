package domain.exceptions;

public class HospedeInvalidoException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HospedeInvalidoException(String message) {
		super(message);
	}

	public HospedeInvalidoException(Throwable exception) {
		super(exception);
	}

}

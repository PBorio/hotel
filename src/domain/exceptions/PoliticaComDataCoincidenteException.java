package domain.exceptions;

public class PoliticaComDataCoincidenteException extends HotelException {

	public PoliticaComDataCoincidenteException(Throwable exception) {
		super(exception);
	}

	public PoliticaComDataCoincidenteException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534094965441625443L;

}

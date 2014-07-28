package domain.exceptions;

public class PagamentoInvalidoException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagamentoInvalidoException(Throwable exception) {
		super(exception);
	}
	
	public PagamentoInvalidoException(String message) {
		super(message);
	}

}

package domain.exceptions;

public class DataFechamentoNaoInformadoException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataFechamentoNaoInformadoException(Throwable exception) {
		super(exception);
	}

	public DataFechamentoNaoInformadoException(String message) {
		super(message);
	}

}

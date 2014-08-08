package domain.exceptions;

public class PoliticaPadraoJaExistenteException extends HotelException {

	public PoliticaPadraoJaExistenteException(Throwable exception) {
		super(exception);
	}

	public PoliticaPadraoJaExistenteException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 8236434591346006527L;

}

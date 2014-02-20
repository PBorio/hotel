package domain.exceptions;

public class HotelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HotelException(String message){
		super(message);
	}
	
	public HotelException(Throwable exception){
		super(exception);
	}

}

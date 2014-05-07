package edu.hbut.livestock.http.coding;

public class MarshallException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7428400190712844916L;

	public MarshallException() {
		super();
	}

	public MarshallException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public MarshallException(String detailMessage) {
		super(detailMessage);
	}

	public MarshallException(Throwable throwable) {
		super(throwable);
	}

}

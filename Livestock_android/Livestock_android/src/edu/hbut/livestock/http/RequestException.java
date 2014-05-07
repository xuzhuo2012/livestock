package edu.hbut.livestock.http;

public class RequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2152106144247327357L;

	public RequestException() {
		super();
	}

	public RequestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public RequestException(String detailMessage) {
		super(detailMessage);
	}

	public RequestException(Throwable throwable) {
		super(throwable);
	}

}

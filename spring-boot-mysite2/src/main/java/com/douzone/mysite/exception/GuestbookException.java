package com.douzone.mysite.exception;

public class GuestbookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798306718846238537L;

	public GuestbookException(String message) {
		super(message);
	}
	
	public GuestbookException() {
		super("GuestbookException");
	}
}

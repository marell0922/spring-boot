package com.douzone.mysite.exception;

public class BoardDaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BoardDaoException() {
		super("BoardExcpetion");
	}
	
	public BoardDaoException(String message) {
		super(message);
	}

}

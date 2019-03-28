package com.douzone.mysite.exception;

public class UserDaoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6974172627517026775L;
	
	public UserDaoException() {
		// TODO Auto-generated constructor stub
		super("UserDao Exception");
	}
	public UserDaoException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}

package com.venky.iban.exception;

public class InvalidInputException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8758439354307902344L;

	public InvalidInputException(String msg){
		super(msg);
	}
}

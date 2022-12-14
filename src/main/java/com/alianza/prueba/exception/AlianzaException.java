package com.alianza.prueba.exception;

import org.springframework.http.HttpStatus;

public class AlianzaException extends Exception{
	
	private final HttpStatus httpStatus;
	
	public AlianzaException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}

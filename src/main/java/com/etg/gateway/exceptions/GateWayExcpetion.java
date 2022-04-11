package com.etg.gateway.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GateWayExcpetion extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -663263648705679003L;
	private HttpStatus code;

	public GateWayExcpetion(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

}

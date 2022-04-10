package com.etg.gateway.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FixerApiResponseException extends RuntimeException {

	private static final long serialVersionUID = -990265458567550834L;
	private HttpStatus code;

	public FixerApiResponseException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

}

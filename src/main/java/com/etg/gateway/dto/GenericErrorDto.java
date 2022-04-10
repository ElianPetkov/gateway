package com.etg.gateway.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericErrorDto {

	private final String message;
	private final ZonedDateTime timeStamp;

	public GenericErrorDto(String message, ZonedDateTime timeStamp) {
		this.message = message;
		this.timeStamp = timeStamp;
	}
}

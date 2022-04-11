package com.etg.gateway.exceptions.handling;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.etg.gateway.dto.GenericErrorDto;

@ControllerAdvice
public class ExceptionHandling {
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception) {
		logger.error("Unexpected error! ", exception);
		GenericErrorDto genericErrorDto = new GenericErrorDto("Please contact the service",
				ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<>(genericErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

package com.etg.gateway.exceptions.handling;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.etg.gateway.common.Constants;
import com.etg.gateway.dto.GenericErrorDto;
import com.etg.gateway.exceptions.GateWayExcpetion;

@ControllerAdvice
public class ExceptionHandling {
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<GenericErrorDto> exception(Exception exception) {
		logger.error(Constants.UNEXPECTED_ERROR_MESSAGE, exception);

		GenericErrorDto genericErrorDto = new GenericErrorDto(Constants.CONTACT_SERVICE_MESSAGE,
				ZonedDateTime.now(ZoneId.of(Constants.UTC)));

		return new ResponseEntity<>(genericErrorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = GateWayExcpetion.class)
	public ResponseEntity<GenericErrorDto> exception(GateWayExcpetion exception) {
		logger.error("GateWayExcpetion occure ", exception);

		GenericErrorDto genericErrorDto = new GenericErrorDto(exception.getMessage(),
				ZonedDateTime.now(ZoneId.of(Constants.UTC)));

		return new ResponseEntity<>(genericErrorDto, exception.getCode());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<List<GenericErrorDto>> exception(MethodArgumentNotValidException e) {
		logger.error("ConstraintViolationException occure ", e);

		List<GenericErrorDto> errors = e.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> new GenericErrorDto(fieldError.getDefaultMessage(),
						ZonedDateTime.now(ZoneId.of(Constants.UTC))))
				.collect(Collectors.toList());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}

package com.flat.wallet.rest;

import com.flat.wallet.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		logger.error("Error occurred: ", e);
		return "There was an unexpected error";
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntityNotFound(EntityNotFoundException ex) {
		logger.error("Error occurred: ", ex);
		return "Entity not found";
	}

}

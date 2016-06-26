package com.flat.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

	public EntityNotFoundException(Class<?> objectType, Long objectId) {
		super(String.format("Object with type: %s and id: %d not found", objectType.toString(), objectId));
	}
}

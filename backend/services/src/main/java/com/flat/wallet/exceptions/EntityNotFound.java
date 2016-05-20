package com.flat.wallet.exceptions;

public class EntityNotFound extends Exception {

	public EntityNotFound(Class<?> objectType, Long objectId) {
		super(String.format("Object with type: %s and id: %d not found", objectType.toString(), objectId));
	}
}

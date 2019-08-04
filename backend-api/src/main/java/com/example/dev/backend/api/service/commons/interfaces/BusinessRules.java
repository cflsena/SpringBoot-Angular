package com.example.dev.backend.api.service.commons.interfaces;

public interface BusinessRules<Entity extends Object> {
	
	/**
	 * Method for validating object save
	 * 
	 * @param Entity
	 *            - parametro a ser validado
	 */
	void validationSave(Entity e);

	/**
	 * Method for validating object delete
	 * 
	 * @param Entity
	 *            - parametro a ser validado
	 */
	void validationDelete(Entity e);
}

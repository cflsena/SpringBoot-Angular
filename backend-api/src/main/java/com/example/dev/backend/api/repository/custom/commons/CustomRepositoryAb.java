package com.example.dev.backend.api.repository.custom.commons;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class CustomRepositoryAb {

	@PersistenceContext
	protected EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

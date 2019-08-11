package com.example.dev.backend.api.service.commons.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericService <Entity extends Object, ID extends Serializable> {
	
	/**
	 * Method that returns repository access used by Spring Data
	 * 
	 * @return TemplateGenericRepository {@linkplain TemplateGenericRepository}
	 */
	JpaRepository<Entity, ID> getRepository();

	/**
	 * Method to persistence (save or update behavior)
	 * 
	 * @param e
	 *            Entity
	 * @return Object Object
	 */
	Object save(Entity e);

	List<Entity> saveInBatch(List<Entity> e);

	/**
	 * Method to delete a JPA entity
	 * 
	 * @param e
	 *            Entity
	 *
	 */
	void delete(Entity e);
	
	/**
	 * Method to delete a JPA entity
	 * 
	 * @param e
	 *            Entity
	 *
	 */
	void deleteById(ID e);

	/**
	 * Method to find by primary key using Spring data structure
	 * 
	 * @param id
	 *            ID
	 * @return Optional<Object> Optional<Object>
	 */
	Optional<Object> findById(ID id);

	/**
	 * Method to find all entities using Spring data structure
	 * data
	 * 
	 * @return List<Entity> List<Entity>
	 */
	List<Entity> findAll();

	/**
	 * Method to find by filter using Spring data structure
	 * 
	 * @return List<Entity> List<Entity>
	 */
	List<Entity> findByFilter(Entity e);

	/**
	 * Method that returns total records from that entity
	 *
	 * @return Long Long
	 */
	Long count();
}

package com.example.dev.backend.api.controller.commons;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dev.backend.api.event.CreatedResourceEvent;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

@Controller
public abstract class GenericController<Entity, ID> {

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	protected List<Entity> findAll() {
		return getService().findAll();
	}

	@GetMapping(path = "/{id}")
	protected ResponseEntity<Entity> find(@PathVariable("id") ID id) {
		Optional<Entity> op = getService().findById((Long) id);
		return op.isPresent() ? ResponseEntity.ok((Entity) op.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@SuppressWarnings("unchecked")
	protected ResponseEntity<Entity> create(@Valid @RequestBody Entity entityToSave, HttpServletResponse response) {
		Entity entity = (Entity) getService().save(entityToSave);
		publisher.publishEvent(new CreatedResourceEvent(this, response, 0L));
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/{id}")
	protected ResponseEntity<Entity> alter(@PathVariable ID id, @Valid @RequestBody Entity entityRequest,
			HttpServletResponse response) {
		Entity entityUpdated = (Entity) getService().update(entityRequest, (Long) id);
		publisher.publishEvent(new CreatedResourceEvent(this, response, 0L));
		return ResponseEntity.ok(entityUpdated);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	protected void delete(@PathVariable("id") ID id) {
		getService().deleteById((Long) id);
	}
	
	protected ApplicationEventPublisher getPublisher() {
		return this.publisher;
	}

	protected abstract GenericService<Entity, Long> getService();
}

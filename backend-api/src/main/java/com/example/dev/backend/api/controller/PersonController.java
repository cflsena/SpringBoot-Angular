package com.example.dev.backend.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.event.CreatedResourceEvent;
import com.example.dev.backend.api.service.PersonService;

@RestController
@RequestMapping("/pessoas")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<PersonEntity> findAll() {
		return personService.findAll();
	}

	@PostMapping
	public ResponseEntity<PersonEntity> save(@Valid @RequestBody PersonEntity personEntityRequest,
			HttpServletResponse response) {
		PersonEntity personEntitySaved = (PersonEntity) personService.save(personEntityRequest);
		publisher.publishEvent(new CreatedResourceEvent(this, response, personEntitySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(personEntitySaved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonEntity> findById(@PathVariable Long id) {
		Optional<PersonEntity> op = personService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((PersonEntity) op.get()) : ResponseEntity.notFound().build();

	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody PersonEntity personEntityRequest) {
		personService.deleteById(personEntityRequest.getId());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonEntity> update(@PathVariable Long id,
			@Valid @RequestBody PersonEntity personEntityRequest, HttpServletResponse response) {
		PersonEntity personEntityUpdated = (PersonEntity) personService.update(personEntityRequest, id);
		publisher.publishEvent(new CreatedResourceEvent(this, response, personEntityUpdated.getId()));
		return ResponseEntity.ok(personEntityUpdated);
	}

	@PatchMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveProperty(@PathVariable Long id, @Valid @RequestBody Boolean statusActive,
			HttpServletResponse response) {
		personService.updateActiveProperty(statusActive, id);
	}
}

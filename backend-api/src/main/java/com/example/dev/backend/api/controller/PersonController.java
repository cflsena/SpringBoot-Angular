package com.example.dev.backend.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.constraint.ApiMappingContraint;
import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.event.CreatedResourceEvent;
import com.example.dev.backend.api.service.PersonService;

@RestController
@RequestMapping(value = ApiMappingContraint.BASE_API_URL + "/pessoas")
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
		Optional<Object> op = personService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((PersonEntity) op.get()) : ResponseEntity.notFound().build();

	}
}

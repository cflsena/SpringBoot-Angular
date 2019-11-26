package com.example.dev.backend.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.controller.commons.GenericController;
import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.service.PersonService;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

@RestController
@RequestMapping("/pessoas")
public class PersonController extends GenericController<PersonEntity, Long> {

	@Autowired
	private PersonService personService;

	@Override
	protected GenericService<PersonEntity, Long> getService() {
		return this.personService;
	}

	@PatchMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateActiveProperty(@PathVariable Long id, @Valid @RequestBody Boolean statusActive,
			HttpServletResponse response) {
		personService.updateActiveProperty(statusActive, id);
	}
}

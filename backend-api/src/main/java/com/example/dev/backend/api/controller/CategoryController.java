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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.constraint.ApiMappingContraint;
import com.example.dev.backend.api.entity.CategoryEntity;
import com.example.dev.backend.api.event.CreatedResourceEvent;
import com.example.dev.backend.api.service.CategoryService;

@RestController
@RequestMapping(value = ApiMappingContraint.BASE_API_URL + "/categorias")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<CategoryEntity> findAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryEntity> findById(@PathVariable Long id) {
		Optional<Object> op = categoryService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((CategoryEntity) op.get()) : ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<CategoryEntity> save(@Valid @RequestBody CategoryEntity categoryEntityRequest,
			HttpServletResponse response) {
		CategoryEntity categoryEntitySaved = (CategoryEntity) categoryService.save(categoryEntityRequest);
		publisher.publishEvent(new CreatedResourceEvent(this, response, categoryEntitySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryEntitySaved);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@RequestBody CategoryEntity categoryEntityRequest) {
		categoryService.deleteById(categoryEntityRequest.getId());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryEntity> update(@PathVariable Long id,
			@Valid @RequestBody CategoryEntity categoryEntityRequest, HttpServletResponse response) {
		CategoryEntity categoryEntityUpdated = (CategoryEntity) categoryService.update(categoryEntityRequest, id);
		publisher.publishEvent(new CreatedResourceEvent(this, response, categoryEntityUpdated.getId()));
		return ResponseEntity.ok(categoryEntityUpdated);
	}
}

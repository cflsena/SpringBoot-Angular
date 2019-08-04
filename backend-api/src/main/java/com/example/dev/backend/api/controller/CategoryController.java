package com.example.dev.backend.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dev.backend.api.constraint.ApiMappingContraint;
import com.example.dev.backend.api.entity.CategoryEntity;
import com.example.dev.backend.api.service.CategoryService;

@RestController
@RequestMapping(value = ApiMappingContraint.BASE_API_URL + "/categorias")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<CategoryEntity> findAll() {
		return categoryService.findAll();
	}

	@PostMapping
	public ResponseEntity<CategoryEntity> save(@Valid @RequestBody CategoryEntity categoryEntityRequest,
			HttpServletResponse response) {

		CategoryEntity categoryEntitySaved = (CategoryEntity) categoryService.save(categoryEntityRequest);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(categoryEntitySaved.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.ok(categoryEntitySaved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryEntity> findById(@PathVariable Long id) {
		Optional<Object> op = categoryService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((CategoryEntity) op.get()) : ResponseEntity.notFound().build();

	}
}

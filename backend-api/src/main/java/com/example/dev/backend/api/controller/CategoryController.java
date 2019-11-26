package com.example.dev.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.controller.commons.GenericController;
import com.example.dev.backend.api.entity.CategoryEntity;
import com.example.dev.backend.api.service.CategoryService;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

@RestController
@RequestMapping("/categorias")
public class CategoryController extends GenericController<CategoryEntity, Long>{

	@Autowired
	private CategoryService categoryService;

	@Override
	protected GenericService<CategoryEntity, Long> getService() {
		return this.categoryService;
	}

}

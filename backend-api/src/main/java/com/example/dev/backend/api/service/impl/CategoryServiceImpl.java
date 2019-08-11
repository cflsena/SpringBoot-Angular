package com.example.dev.backend.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.dev.backend.api.entity.CategoryEntity;
import com.example.dev.backend.api.repository.CategoryRepository;
import com.example.dev.backend.api.service.CategoryService;
import com.example.dev.backend.api.service.commons.GenericServiceAb;

@Service
public class CategoryServiceImpl extends GenericServiceAb<CategoryEntity, Long> implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public JpaRepository<CategoryEntity, Long> getRepository() {
		return this.categoryRepository;
	}
}

package com.example.deve.backend.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.deve.backend.api.entity.CategoryEntity;
import com.example.deve.backend.api.repository.CategoryRepository;
import com.example.deve.backend.api.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Override
	public List<CategoryEntity> findAll() {
		return this.repository.findAll();
	}

}

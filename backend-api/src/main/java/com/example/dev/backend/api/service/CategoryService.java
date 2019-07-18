package com.example.dev.backend.api.service;

import java.util.List;

import com.example.dev.backend.api.entity.CategoryEntity;

public interface CategoryService {

	List<CategoryEntity> findAll();
}

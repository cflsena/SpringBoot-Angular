package com.example.deve.backend.api.service;

import java.util.List;

import com.example.deve.backend.api.entity.CategoryEntity;

public interface CategoryService {

	List<CategoryEntity> findAll();
}

package com.example.dev.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dev.backend.api.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}

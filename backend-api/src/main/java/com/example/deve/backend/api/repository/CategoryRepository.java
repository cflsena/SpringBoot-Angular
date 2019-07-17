package com.example.deve.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.deve.backend.api.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}

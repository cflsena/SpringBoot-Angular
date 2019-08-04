package com.example.dev.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dev.backend.api.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}

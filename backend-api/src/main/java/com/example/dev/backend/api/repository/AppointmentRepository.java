package com.example.dev.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dev.backend.api.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long>{

}

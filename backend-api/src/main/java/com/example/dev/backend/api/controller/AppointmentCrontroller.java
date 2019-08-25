package com.example.dev.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.constraint.ApiMappingContraint;
import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.CategoryEntity;
import com.example.dev.backend.api.service.AppointmentService;

@RestController
@RequestMapping(value = ApiMappingContraint.BASE_API_URL + "/lancamentos")
public class AppointmentCrontroller {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<AppointmentEntity> findAll(){
		return appointmentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppointmentEntity> findById(@PathVariable Long id) {
		Optional<Object> op = appointmentService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((AppointmentEntity) op.get()) : ResponseEntity.notFound().build();
	}
}

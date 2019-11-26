package com.example.dev.backend.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.event.CreatedResourceEvent;
import com.example.dev.backend.api.service.AppointmentService;

@RestController
@RequestMapping("/lancamentos")
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
		Optional<AppointmentEntity> op = appointmentService.findById(id);
		return op.isPresent() ? ResponseEntity.ok((AppointmentEntity) op.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<AppointmentEntity> save(@Valid @RequestBody AppointmentEntity appointmentEntityRequest,
			HttpServletResponse response) {
		AppointmentEntity appointmentEntitySaved = appointmentService.saveAppointmentEntity(appointmentEntityRequest);
		publisher.publishEvent(new CreatedResourceEvent(this, response, appointmentEntitySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentEntitySaved);
	}
}

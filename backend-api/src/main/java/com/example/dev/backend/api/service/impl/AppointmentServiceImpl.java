package com.example.dev.backend.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.repository.AppointmentRepository;
import com.example.dev.backend.api.service.AppointmentService;
import com.example.dev.backend.api.service.commons.GenericServiceAb;

@Service
public class AppointmentServiceImpl extends GenericServiceAb<AppointmentEntity, Long> implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public JpaRepository<AppointmentEntity, Long> getRepository() {
		return this.appointmentRepository;
	}

}

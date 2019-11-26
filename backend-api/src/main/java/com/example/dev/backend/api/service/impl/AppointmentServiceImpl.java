package com.example.dev.backend.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.repository.AppointmentRepository;
import com.example.dev.backend.api.repository.PersonRepository;
import com.example.dev.backend.api.service.AppointmentService;
import com.example.dev.backend.api.service.commons.GenericServiceAb;
import com.example.dev.backend.api.service.exception.InativePersonException;
import com.example.dev.backend.api.service.exception.NonexistentPersonException;

@Service
public class AppointmentServiceImpl extends GenericServiceAb<AppointmentEntity, Long> implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public JpaRepository<AppointmentEntity, Long> getRepository() {
		return this.appointmentRepository;
	}
	
	public AppointmentEntity saveAppointmentEntity(AppointmentEntity appointmentEntity) {

		Optional<PersonEntity> op = personRepository.findById(appointmentEntity.getPerson().getId());

		if (!op.isPresent()) {
			throw new NonexistentPersonException();
		} else {
			PersonEntity personEntity = (PersonEntity) op.get();
			if (!personEntity.getActive()) {
				throw new InativePersonException();
			}
		}
		
		return (AppointmentEntity) super.save(appointmentEntity);
	}
}

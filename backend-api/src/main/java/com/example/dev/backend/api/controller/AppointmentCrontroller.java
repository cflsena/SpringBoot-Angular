package com.example.dev.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.controller.commons.GenericController;
import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.service.AppointmentService;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

@RestController
@RequestMapping("/lancamentos")
public class AppointmentCrontroller extends GenericController<AppointmentEntity, Long> {

	@Autowired
	private AppointmentService appointmentService;

	@Override
	protected GenericService<AppointmentEntity, Long> getService() {
		return this.appointmentService;
	}
}

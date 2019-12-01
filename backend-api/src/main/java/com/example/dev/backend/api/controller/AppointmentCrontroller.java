package com.example.dev.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.controller.commons.GenericController;
import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;
import com.example.dev.backend.api.service.AppointmentService;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;
import com.example.dev.backend.api.utils.GenericUtils;

@RestController
@RequestMapping("/lancamentos")
public class AppointmentCrontroller extends GenericController<AppointmentEntity, Long> {

	@Autowired
	private AppointmentService appointmentService;

	@Override
	protected GenericService<AppointmentEntity, Long> getService() {
		return this.appointmentService;
	}

	@GetMapping("/filtro")
	public ResponseEntity<List<AppointmentEntity>> findByfilter(AppointmentFilter appointmentFilter) {
		List<AppointmentEntity> appointmentEntityList = appointmentService.findByfilter(appointmentFilter);
		return GenericUtils.isNotEmpytAndNotNull(appointmentEntityList) ? ResponseEntity.ok(appointmentEntityList)
				: ResponseEntity.noContent().build();
	}
}

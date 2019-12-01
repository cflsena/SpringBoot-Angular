package com.example.dev.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dev.backend.api.commons.model.PageCustom;
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
	public ResponseEntity<PageCustom> findByfilter(AppointmentFilter appointmentFilter) {
		PageCustom appointmentPageCustom = appointmentService.findByfilter(appointmentFilter);
		return GenericUtils.isNotEmpytAndNotNull(appointmentPageCustom.getListObject()) ? ResponseEntity.ok(appointmentPageCustom)
				: ResponseEntity.noContent().build();
	}
}

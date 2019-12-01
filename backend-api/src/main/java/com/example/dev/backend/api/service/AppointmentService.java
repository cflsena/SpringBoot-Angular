package com.example.dev.backend.api.service;

import java.util.List;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

public interface AppointmentService extends GenericService<AppointmentEntity, Long> {

	List<AppointmentEntity> findByfilter(AppointmentFilter appointmentFilter);

}

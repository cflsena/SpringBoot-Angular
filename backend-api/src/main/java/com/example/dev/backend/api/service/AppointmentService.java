package com.example.dev.backend.api.service;

import com.example.dev.backend.api.commons.model.PageCustom;
import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

public interface AppointmentService extends GenericService<AppointmentEntity, Long> {

	PageCustom findByfilter(AppointmentFilter appointmentFilter);

}

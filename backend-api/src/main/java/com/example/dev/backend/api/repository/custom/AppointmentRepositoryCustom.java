package com.example.dev.backend.api.repository.custom;

import java.util.List;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;

public interface AppointmentRepositoryCustom {
	public List<AppointmentEntity> findByfilter(AppointmentFilter appointmentFilter);
}

package com.example.dev.backend.api.repository.custom;

import com.example.dev.backend.api.commons.model.PageCustom;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;

public interface AppointmentRepositoryCustom {
	PageCustom findByfilter(AppointmentFilter appointmentFilter);
}

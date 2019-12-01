package com.example.dev.backend.api.entity.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.dev.backend.api.commons.model.PaginatorCustom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFilter extends PaginatorCustom {

	private static final long serialVersionUID = -2199928576613099507L;

	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateTo;
}

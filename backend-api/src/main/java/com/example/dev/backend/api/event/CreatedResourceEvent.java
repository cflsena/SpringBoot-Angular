package com.example.dev.backend.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6789948148625658721L;

	private HttpServletResponse response;
	private Long code;

	public CreatedResourceEvent(Object source, HttpServletResponse response, Long code) {
		super(source);
		this.response = response;
		this.code = code;
	}

}

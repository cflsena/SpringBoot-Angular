package com.example.dev.backend.api.listener;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dev.backend.api.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

	@Override
	public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
		this.addHeaderLocation(createdResourceEvent);
	}

	private void addHeaderLocation(CreatedResourceEvent createdResourceEvent) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(createdResourceEvent.getCode()).toUri();
		createdResourceEvent.getResponse().setHeader("Location", uri.toASCIIString());
	}

}

package com.example.dev.backend.api.service;

import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.service.commons.interfaces.GenericService;

public interface PersonService extends GenericService<PersonEntity, Long> {

	void updateActiveProperty(Boolean statusActive, Long id);

}

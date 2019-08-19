package com.example.dev.backend.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.dev.backend.api.entity.PersonEntity;
import com.example.dev.backend.api.repository.PersonRepository;
import com.example.dev.backend.api.service.PersonService;
import com.example.dev.backend.api.service.commons.GenericServiceAb;
import com.example.dev.backend.api.utils.GenericUtils;

@Service
public class PersonServiceImpl extends GenericServiceAb<PersonEntity, Long> implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public JpaRepository<PersonEntity, Long> getRepository() {
		return this.personRepository;
	}

	@Override
	public void updateActiveProperty(Boolean statusActive, Long id) {
		PersonEntity personEntityUpdated = (PersonEntity) findAnyEntityById(id);
		if (GenericUtils.isNotEmpytOrNotNull(personEntityUpdated)) {
			personEntityUpdated.setActive(statusActive);
			getRepository().save(personEntityUpdated);
			getRepository().flush();
		}
	}
}

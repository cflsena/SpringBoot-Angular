package com.example.dev.backend.api.service.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import com.example.dev.backend.api.service.commons.interfaces.GenericService;
import com.example.dev.backend.api.utils.GenericUtils;

public abstract class GenericServiceAb<Entity extends Object, ID extends Serializable>
		implements GenericService<Entity, ID> {

	@Override
	@Transactional
	public Object save(Entity e) {
		e = getRepository().save(e);
		getRepository().flush();
		return e;
	}
	
	@Override
	@Transactional
	public Object update(Entity e, ID id) {
		Optional<Object> op = (Optional) getRepository().findById(id);
		if (!op.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		Entity ob = (Entity) op.get();
		if (GenericUtils.isNotEmpytOrNotNull(e)) {
			BeanUtils.copyProperties(e, ob, "id");
			ob = getRepository().save(ob);
			getRepository().flush();
		} else {
			throw new EmptyResultDataAccessException(1);
		}
		return ob;
	}

	@Override
	@Transactional
	public List<Entity> saveInBatch(List<Entity> e) {
		List<Entity> list = getRepository().saveAll(e);
		getRepository().flush();
		return list;
	}

	@Override
	@Transactional
	public void delete(Entity e) {
		getRepository().delete(e);
		getRepository().flush();
	}
	
	@Override
	@Transactional
	public void deleteById(ID id) {
		getRepository().deleteById(id);
		getRepository().flush();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Object> findById(ID id) {
		return (Optional) getRepository().findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entity> findAll() {
		return getRepository().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Entity> findByFilter(Entity e) {
		Example<Entity> example = Example.of(e);
		return getRepository().findAll(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return getRepository().count();
	}
}

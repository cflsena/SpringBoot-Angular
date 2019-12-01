package com.example.dev.backend.api.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.example.dev.backend.api.entity.AppointmentEntity;
import com.example.dev.backend.api.entity.AppointmentEntity_;
import com.example.dev.backend.api.entity.filter.AppointmentFilter;
import com.example.dev.backend.api.repository.custom.AppointmentRepositoryCustom;
import com.example.dev.backend.api.repository.custom.commons.CustomRepositoryAb;
import com.example.dev.backend.api.utils.GenericUtils;

@Component
public class AppointmentRepositoryCustomImpl extends CustomRepositoryAb implements AppointmentRepositoryCustom {

	@Override
	public List<AppointmentEntity> findByfilter(AppointmentFilter appointmentFilter) {

		CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AppointmentEntity> criteriaQuery = criteriaBuilder.createQuery(AppointmentEntity.class);
		Root<AppointmentEntity> root = criteriaQuery.from(AppointmentEntity.class);

		Predicate[] predicates = createRestrictions(appointmentFilter, criteriaBuilder, root);
		criteriaQuery.where(predicates);

		TypedQuery<AppointmentEntity> typedQuery = this.getEntityManager().createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	private Predicate[] createRestrictions(AppointmentFilter appointmentFilter, CriteriaBuilder criteriaBuilder,
			Root<AppointmentEntity> root) {

		List<Predicate> predicateList = new ArrayList<>();

		if (!StringUtils.isBlank(appointmentFilter.getDescription())) {
			String description = appointmentFilter.getDescription();
			predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(AppointmentEntity_.description)),
					"%" + description.toLowerCase() + "%"));
		}

		if (GenericUtils.isNotEmpytAndNotNull(appointmentFilter.getDueDateFrom())) {
			predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(AppointmentEntity_.dueDate),
					appointmentFilter.getDueDateFrom()));
		}

		if (GenericUtils.isNotEmpytAndNotNull(appointmentFilter.getDueDateTo())) {
			predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get(AppointmentEntity_.dueDate),
					appointmentFilter.getDueDateTo()));
		}
		return predicateList.toArray(new Predicate[predicateList.size()]);
	}
}

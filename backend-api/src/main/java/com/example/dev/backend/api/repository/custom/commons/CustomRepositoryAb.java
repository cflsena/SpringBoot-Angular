package com.example.dev.backend.api.repository.custom.commons;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.dev.backend.api.commons.model.PageCustom;
import com.example.dev.backend.api.commons.model.PaginatorCustom;

public abstract class CustomRepositoryAb<Entity extends Object, Serializable> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected <T> Long count(Class<Entity> clazz, PaginatorCustom filter, Predicate[] predicates) {
		CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Entity> root = criteriaQuery.from(clazz);
		criteriaQuery.where(predicates);
		criteriaQuery.select(criteriaBuilder.count(root));
		return this.getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	protected void executeCriteriaQuery(PageCustom pageCustomResult, PaginatorCustom filter,
			CriteriaQuery<Entity> criteriaQuery, Long count, Predicate[] predicates) {
		if (count > 0) {
			criteriaQuery.where(predicates);
			TypedQuery<Entity> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
			this.setPagination(typedQuery, filter);
			pageCustomResult.setListObject(typedQuery.getResultList());
			return;
		}
		if (count < 0) {
			pageCustomResult.setListObject(new ArrayList<>());
			return;
		}
	}

	protected void setPagination(Query query, PaginatorCustom paginator) {
		query.setMaxResults(paginator.getPageSize());
		query.setFirstResult(paginator.getPageIndex() * paginator.getPageSize());
	}
}

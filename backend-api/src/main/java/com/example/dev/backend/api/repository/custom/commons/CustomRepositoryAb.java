package com.example.dev.backend.api.repository.custom.commons;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.dev.backend.api.commons.model.PageCustom;
import com.example.dev.backend.api.commons.model.PaginatorCustom;

public abstract class CustomRepositoryAb {

	@PersistenceContext
	protected EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected <T> Long count(Class<T> clazz, PaginatorCustom filter, Predicate[] predicates) {
		CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQueryCount.from(clazz);
		root.alias("root_alias");
		criteriaQueryCount.where(predicates);
		criteriaQueryCount.select(criteriaBuilder.count(root));
		return this.getEntityManager().createQuery(criteriaQueryCount).getSingleResult();
	}

	protected void executeCriteriaQuery(PageCustom pageCustomResult, PaginatorCustom filter,
			CriteriaQuery<?> criteriaQuery, Long count, Predicate[] predicates) {
		if (count > 0) {
			criteriaQuery.where(predicates);
			TypedQuery<?> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
			this.setPagination(typedQuery, filter);
			pageCustomResult.setListObject(typedQuery.getResultList());
			return;
		}
		if (count < 0) {
			pageCustomResult.setListObject(new ArrayList<>());
			return;
		}
	}

	protected void setPagination(TypedQuery<?> query, PaginatorCustom paginator) {
		query.setFirstResult(paginator.getPageIndex() * paginator.getPageSize());
		query.setMaxResults(paginator.getPageSize());
	}
}

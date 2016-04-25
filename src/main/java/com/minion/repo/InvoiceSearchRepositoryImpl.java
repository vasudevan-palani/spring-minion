package com.minion.repo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.minion.Utils;
import com.minion.model.view.InvoiceSearch;

public class InvoiceSearchRepositoryImpl implements InvoiceSearchRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public List<InvoiceSearch> findInvoices(String invoiceId, Date startDate, Date endDate, Date invoiceDate,
			String empId, String poNumber, String projectId,String statusId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<InvoiceSearch> query = builder.createQuery(InvoiceSearch.class);
		Root<InvoiceSearch> root = query.from(InvoiceSearch.class);
		List<Predicate> pdList = new ArrayList<Predicate>();

		if (Utils.notEmpty(invoiceId)) {
			pdList.add(builder.equal(root.get("invoiceId"), invoiceId));
		}
		if (Utils.notEmpty(startDate)) {
			pdList.add(builder.greaterThanOrEqualTo(root.get("startDate"), startDate));
		}
		if (Utils.notEmpty(endDate)) {
			pdList.add(builder.lessThanOrEqualTo(root.get("endDate"), endDate));
		}
		if (Utils.notEmpty(invoiceDate)) {
			pdList.add(builder.equal(root.get("invoiceDate"), invoiceDate));
		}
		if (Utils.notEmpty(poNumber)) {
			pdList.add(builder.equal(root.get("poNumber"), poNumber));
		}
		if (Utils.notEmpty(statusId)) {
			pdList.add(builder.equal(root.get("statusId"), statusId));
		}
		if (Utils.notEmpty(projectId)) {
			pdList.add(builder.equal(root.get("projectId"), projectId));
		}

		query.where(builder.and(pdList.toArray(new Predicate[pdList.size()])));

		return em.createQuery(query).getResultList();
	}

}

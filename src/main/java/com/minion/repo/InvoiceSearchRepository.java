package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.view.InvoiceSearch;

public interface InvoiceSearchRepository extends CrudRepository<InvoiceSearch, Serializable >,InvoiceSearchRepositoryCustom{

	InvoiceSearch findByInvoiceId(String invoiceId);

	InvoiceSearch findById(Integer id);
	
}

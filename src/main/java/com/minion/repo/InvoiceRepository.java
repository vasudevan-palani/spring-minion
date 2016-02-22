package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Serializable >{

	Invoice findByInvoiceId(String invoiceId);
	
}

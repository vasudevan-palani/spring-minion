package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.InvoiceUser;

public interface InvoiceUserRepository extends CrudRepository<InvoiceUser, Serializable >{

	InvoiceUser findByInvoiceIdAndUserId(Integer invoiceId,Integer userId);
	
}

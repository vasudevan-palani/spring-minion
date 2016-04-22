package com.minion.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.InvoiceUser;

public interface InvoiceUserRepository extends CrudRepository<InvoiceUser, Serializable >{

	InvoiceUser findByInvoiceIdAndUserId(Integer invoiceId,Integer userId);

	List<InvoiceUser> findByInvoiceId(Integer invoiceId);
	
}

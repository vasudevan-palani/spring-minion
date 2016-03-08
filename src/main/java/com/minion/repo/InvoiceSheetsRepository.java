package com.minion.repo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.InvoiceSheets;

public interface InvoiceSheetsRepository extends CrudRepository<InvoiceSheets, Serializable >{

	@Query("SELECT is FROM invoice_sheets is where is.start_date=:start_date and is.invoice_num = :invoice_num and is.full_name = :full_name")
	List<InvoiceSheets> findObject(@Param("start_date")Date startDate, @Param("invoice_num")String invoiceNum, @Param("full_name")String fullName);
}

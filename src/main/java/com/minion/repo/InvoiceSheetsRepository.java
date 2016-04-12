package com.minion.repo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.InvoiceSheets;

public interface InvoiceSheetsRepository extends CrudRepository<InvoiceSheets, Serializable >{

	@Query("SELECT ins FROM invoice_sheets ins where ins.startDate=:start_date and ins.invoiceNum = :invoice_num and ins.fullName = :full_name")
	List<InvoiceSheets> findObject(@Param("start_date")Date startDate, @Param("invoice_num")String invoiceNum, @Param("full_name")String fullName);
}

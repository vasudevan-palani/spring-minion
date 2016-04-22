package com.minion.repo;

import java.sql.Date;
import java.util.List;

import com.minion.model.view.InvoiceSearch;

public interface InvoiceSearchRepositoryCustom {
	List<InvoiceSearch> findInvoices(String invoiceId, Date startDate, Date endDate, Date invoiceDate, String empId,
			String poNumber, String projectId, String statusId);
}

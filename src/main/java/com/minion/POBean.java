package com.minion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class POBean {
	private final int PO_NUMBER = 1;
	private final int PO_REVISION = 2;
	private final int REQUESTED_DATE = 3;
	private final int BUYER = 4;
	private final int REQUESTOR = 5;
	private final int RESOURCE_DESC = 6;
	private final int PROJECT_ID = 7;
	private final int PROJECT_NAME = 8;
	private final int QUANTITY = 9;
	private final int UNIT_PRICE = 10;
	private final int TOTAL = 11;
	private final int EMPLOYEE_ID = 12;

	
	private String poNumber;
	private String poRevision;
	private Date requestedDate;
	private String buyer;
	private String requestor;
	private String resourceDesc;
	private String projectId;
	private String projectName;
	private float quantity;
	private float rate;
	private float total;
	private String employeeId;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoRevision() {
		return poRevision;
	}

	public void setPoRevision(String poRevision) {
		this.poRevision = poRevision;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void parse(String line) throws ParseException {
		String[] fields = line.split("\\,");

		setPoNumber(fields[PO_NUMBER]);
		setPoRevision(fields[PO_REVISION]);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		setRequestedDate(formatter.parse(fields[REQUESTED_DATE]));
		setBuyer(fields[BUYER]);
		setRequestor(fields[REQUESTOR]);
		setResourceDesc(fields[RESOURCE_DESC]);
		setProjectId(fields[PROJECT_ID]);
		setProjectName(fields[PROJECT_NAME]);
		if (!fields[QUANTITY].trim().equalsIgnoreCase(""))
			setQuantity(Float.parseFloat(fields[QUANTITY]));
		if (!fields[UNIT_PRICE].trim().equalsIgnoreCase(""))
			setRate(Float.parseFloat(fields[UNIT_PRICE]));
		if (!fields[TOTAL].trim().equalsIgnoreCase(""))
			setTotal(Float.parseFloat(fields[TOTAL]));
		if (fields.length > 13)
			setEmployeeId(fields[EMPLOYEE_ID]);
	}

}

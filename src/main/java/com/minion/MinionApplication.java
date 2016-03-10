package com.minion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.minion.dao.CreditNoteDao;
import com.minion.dao.InvoiceDao;
import com.minion.dao.InvoiceUserDao;
import com.minion.dao.PODao;
import com.minion.dao.PORolesDao;
import com.minion.dao.ProjectDao;
import com.minion.dao.UserDao;
import com.minion.loader.BillingSheetLoader;
import com.minion.loader.Loader;
import com.minion.loader.LoadingException;
import com.minion.loader.POLoader;
import com.minion.model.CreditNote;
import com.minion.model.Invoice;
import com.minion.model.PO;
import com.minion.model.Project;
import com.minion.model.User;
import com.minion.repo.InvoiceRepository;
import com.minion.repo.PORepository;

@SpringBootApplication
@EnableAutoConfiguration
public class MinionApplication extends SpringBootServletInitializer {

	public MinionApplication() {

	}

	@Autowired
	private InvoiceRepository invoiceRep;

	@Autowired
	private PORepository poRep;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PODao poDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoiceUserDao invoiceUserDao;

	@Autowired
	private CreditNoteDao creditNoteDao;

	@Autowired
	private PORolesDao poRolesDao;
	
	@Autowired
	private ApplicationContext appContext;
	
	

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public PORolesDao getPoRolesDao() {
		return poRolesDao;
	}

	public void setPoRolesDao(PORolesDao poRolesDao) {
		this.poRolesDao = poRolesDao;
	}

	public CreditNoteDao getCreditNoteDao() {
		return creditNoteDao;
	}

	public void setCreditNoteDao(CreditNoteDao creditNoteDao) {
		this.creditNoteDao = creditNoteDao;
	}

	public InvoiceUserDao getInvoiceUserDao() {
		return invoiceUserDao;
	}

	public void setInvoiceUserDao(InvoiceUserDao invoiceUserDao) {
		this.invoiceUserDao = invoiceUserDao;
	}

	public InvoiceDao getInvoiceDao() {
		return invoiceDao;
	}

	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public InvoiceRepository getInvoiceRep() {
		return invoiceRep;
	}

	public void setInvoiceRep(InvoiceRepository invoiceRep) {
		this.invoiceRep = invoiceRep;
	}

	public PORepository getPoRep() {
		return poRep;
	}

	public void setPoRep(PORepository poRep) {
		this.poRep = poRep;
	}

	@PostConstruct
	@Transactional
	public String run() {

		try {
			//readInvoices();
			//loadKronos();
			loadInvoice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// loadBillingSheets();
		// findFiles();
		System.exit(1);
		return "";
	}

	
	public void loadKronos(){
		Loader kronosldr = (Loader) appContext.getBean("kronosEffortLoader");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\July 2015.xls");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Aug 2015.xls");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Sep 2015.xls");
		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Oct 2015.xls");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Nov 2015.xls");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Dec 2015.xls");
//		kronosldr.load("C:\\Vasu\\tracfone-operations\\kronos-hours\\Jan 2016.xls");
	}
	
	public void loadInvoice(){
		Loader invoiceLdr = (Loader) appContext.getBean("invoiceLoader");
		invoiceLdr.load("C:\\Vasu\\tracfone-operations\\invoice_digitize\\invoice_merged_v1.1_test.xlsm");
	}
	
	private void loadBillingSheets() {
		String filename = "C:/Vasu/tracfone-operations/billing sheets/standard/Billing-Sheet-Sep-2015.csv";
		BillingSheetLoader loader = new BillingSheetLoader(filename);
		try {
			loader.load();
		} catch (LoadingException e) {

			e.printStackTrace();
		}

	}

	private void loadPOCSV() throws NumberFormatException, ParseException, LoadingException {
		POLoader loader = new POLoader("c:/temp/pos.csv");
		loader.setPoRolesDao(poRolesDao);
		loader.setPoDao(poDao);
		loader.load();
	}

	private void findFiles() {
		File directory = new File("c:/vasu/tracfone-operations/pos");
		// File directory = new File("C:/Vasu/tracfone-operations/invoices");
		File[] fList = directory.listFiles();

		// This will reference one line at a time
		String line = null;
		boolean found = false;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("c:/temp/pos-invalid.txt");
			// FileReader fileReader = new
			// FileReader("C:/Vasu/tracfone-operations/pending_invoice.txt");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				found = false;
				line = line.trim();
				// for each name in the path array
				for (File path : fList) {
					if (path.getName().matches(".*" + line + ".*")) {
						found = true;

					}
				}
				if (!found) {
					System.out.println(line);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readInvoices() throws NumberFormatException, ParseException {
		File directory = new File("c:/vasu/tracfone-operations/invoices");

		File[] fList = directory.listFiles();

		// for each name in the path array
		for (File path : fList) {
			if (path.getName().endsWith("txt")) {
				readInvoice(path);
			}
		}
		System.exit(1);
	}

	private void readInvoice(File file) throws NumberFormatException, ParseException {

		// System.out.println(
		// "------------------------------------------------------------------------------------------------");
		// System.out.println("Reading Invoice : " + file.getName());

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			HashMap<String, String> vals = new HashMap<String, String>();
			vals.put("INVOICE", file.getName());
			int resource_count = 0;
			while ((line = bufferedReader.readLine()) != null) {
				boolean matched = false;
				if (line.matches(".*Total Amount Due.*")) {
					line = line.replaceAll(".*Total Amount Due", "Total Amount Due");
					line = line.replaceAll("\\s+", "|");
					vals.put("TOTAL", line.split("\\|")[3]);
					matched = true;
				} else if (line.matches(".*Offshore.*")) {
					matched = true;
					line = line.replaceAll("\\s+", "|");
					vals.put("RESOURCE_" + resource_count, line);
					vals.put("RESOURCE", "Y");
					resource_count++;
				} else if (line.matches(".*Onsite.*")) {
					matched = true;
					line = line.replaceAll("\\s+", "|");
					vals.put("RESOURCE_" + resource_count, line);
					resource_count++;
					vals.put("RESOURCE", "Y");
				} else if (line.matches(".*Billing Period.*")) {
					line = line.replaceAll(".*Billing Period", "Billing Period");
					line = line.replaceAll("\\s+", "|");
					vals.put("BILLING_FROM", line.split("\\|")[2]);
					if (line.split("\\|").length > 4) {
						vals.put("BILLING_TO", line.split("\\|")[4]);
					}
					matched = true;
				} else if (line.matches(".*Credit Note No.*")) {
					line = line.replaceAll(".*Credit Note", "Credit Note");
					line = line.replaceAll("\\s+", "|");
					vals.put("CREDIT NOTE NUMBER", line.split("\\|")[3]);
					matched = true;
				} else if (line.matches(".*Credit Note Date.*")) {
					line = line.replaceAll(".*Credit Note", "Credit Note");
					line = line.replaceAll("\\s+", "|");
					vals.put("CREDIT NOTE DATE", line.split("\\|")[3]);
					matched = true;
				} else if (line.matches(".*Invoice No.*")) {
					line = line.replaceAll(".*Invoice", "Invoice");
					line = line.replaceAll("\\s+", "|");
					vals.put("INVOICE NUMBER", line.split("\\|")[2]);
					matched = true;
				} else if (line.matches(".*Invoice Date.*")) {
					line = line.replaceAll(".*Invoice", "Invoice");
					line = line.replaceAll("\\s+", "|");
					vals.put("INVOICE DATE", line.split("\\|")[2]);
					matched = true;
				} else if (line.matches(".*Original Invoice.*")) {
					line = line.replaceAll(".*Original Invoice", "Original Invoice");

					vals.put("ORIGINAL INVOICE", line.trim().split("\\:")[1]);
					matched = true;
				} else if (line.matches(".*Project ID.*")) {
					line = line.replaceAll(".*Project", "Project");
					line = line.replaceAll("\\s+", "|");
					vals.put("PROJECT ID", line.split("\\|")[2]);
					matched = true;
				} else if (line.matches(".*Project Name.*")) {
					line = line.replaceAll(".*Project", "Project");
					String line1 = line.replaceAll("\\s+", ":");
					vals.put("PROJECT NAME", line.split("\\:")[1].trim());
					matched = true;
				} else if (line.matches(".*PO:.*")) {
					line = line.replaceAll(".*PO:", "PO:");
					line = line.replaceAll("\\s+", "|");

					if (line.split("\\|").length > 1)
						vals.put("PO NUMBER", line.split("\\|")[1]);
					matched = true;
				}

			}
			vals.put("RESOURCE_COUNT", resource_count + "");

			if (true) {
				//System.out.println(
				//		"------------------------------------------------------------------------------------------------------------------------------");
				//System.out.println("INVOICE : " + vals.get("INVOICE"));
				Iterator<String> iterator = vals.keySet().iterator();

				 System.out.println(vals.get("INVOICE") + "#" +
				 vals.get("INVOICE NUMBER") + "#"
				 + vals.get("INVOICE DATE") + "#" + vals.get("CREDIT NOTE DATE") + "#"
				 + vals.get("CREDIT NOTE NUMBER") + "#" + vals.get("ORIGINAL INVOICE") + "#"
				 + vals.get("PROJECT ID") + "#" + vals.get("PROJECT NAME") +
				 "#" + vals.get("BILLING_FROM") + "#"
				 + vals.get("BILLING_TO") + "#" + vals.get("PO NUMBER") + "#"
				 + vals.get("TOTAL") + "#"
				 + vals.get("RESOURCE_COUNT"));

//				System.out.println("INVOICE : " + vals.get("INVOICE"));
//				System.out.println("INVOICE NUMBER : " + vals.get("INVOICE NUMBER"));
//				System.out.println("INVOICE DATE : " + vals.get("INVOICE DATE"));
//				System.out.println("CREDIT NOTE DATE : " + vals.get("CREDIT NOTE DATE"));
//				System.out.println("CREDIT NOTE NUMBER : " + vals.get("CREDIT NOTE NUMBER"));
//				System.out.println("ORIGINAL INVOICE : " + vals.get("ORIGINAL INVOICE"));
//				System.out.println("PROJECT ID : " + vals.get("PROJECT ID"));
//				System.out.println("PROJECT NAME : " + vals.get("PROJECT NAME"));
//				System.out.println("BILLING_FROM :" + vals.get("BILLING_FROM"));
//				System.out.println("BILLING_TO : " + vals.get("BILLING_TO"));
//				System.out.println("PO NUMBER : " + vals.get("PO NUMBER"));
//				System.out.println("TOTAL : " + vals.get("TOTAL"));
//				System.out.println(vals.get("RESOURCE_COUNT"));
//				for (int i = 0; i < resource_count; i++) {
//					System.out.println(vals.get("RESOURCE_" + i));
//				}

				 ArrayList<ResourceBean> beans = updateUsers(vals);
				
				 updateProjects(vals);
				
				 updatePOs(vals);
				
				 updateInvoice(vals, beans);
				
				 updateCreditNotes(vals, beans);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	private void updatePOs(HashMap<String, String> vals) {
		String po_number = vals.get("PO NUMBER");

		if (po_number != null) {

			poDao.createPOIfNotExists(po_number);
		}

	}

	private void updateProjects(HashMap<String, String> vals) {
		String project_id = vals.get("PROJECT ID");
		String project_name = vals.get("PROJECT NAME");

		if (project_id != null) {

			projectDao.createProjectIfNotExists(project_id, project_name);
		}

	}

	private void updateCreditNotes(HashMap<String, String> vals, ArrayList<ResourceBean> resources)
			throws NumberFormatException, ParseException {
		String invoiceFile = vals.get("INVOICE");
		String invoiceNumber = vals.get("INVOICE NUMBER");
		String invoiceDate = vals.get("INVOICE DATE");
		String creditNoteDate = vals.get("CREDIT NOTE DATE");
		String creditNoteNumber = vals.get("CREDIT NOTE NUMBER");
		String orginalInvoice = vals.get("ORIGINAL INVOICE");
		String projectId = vals.get("PROJECT ID");
		String projectName = vals.get("PROJECT NAME");
		String billingFrom = vals.get("BILLING_FROM");
		String billingTo = vals.get("BILLING_TO");
		String poNumber = vals.get("PO NUMBER");
		String total = vals.get("TOTAL");
		total = total.replaceAll("\\,", "");

		if (creditNoteNumber != null && !creditNoteNumber.equalsIgnoreCase("")) {
			Project project = projectDao.findByProjectId(projectId);

			Integer poId = null;
			if (poNumber != null) {
				PO po = poDao.findByPoNumber(poNumber);
				poId = po.getId();
			}

			CreditNote creditNote = creditNoteDao.createCreditNoteIfNotExists(creditNoteNumber, billingFrom, billingTo,
					project.getId(), poId, Float.parseFloat(total), orginalInvoice, invoiceFile, creditNoteDate);

		}

	}

	private void updateInvoice(HashMap<String, String> vals, ArrayList<ResourceBean> resources)
			throws NumberFormatException, ParseException {
		String invoiceFile = vals.get("INVOICE");
		String invoiceNumber = vals.get("INVOICE NUMBER");
		String invoiceDate = vals.get("INVOICE DATE");
		String creditNoteDate = vals.get("CREDIT NOTE DATE");
		String creditNoteNumber = vals.get("CREDIT NOTE NUMBER");
		String orginalInvoice = vals.get("ORIGINAL INVOICE");
		String projectId = vals.get("PROJECT ID");
		String projectName = vals.get("PROJECT NAME");
		String billingFrom = vals.get("BILLING_FROM");
		String billingTo = vals.get("BILLING_TO");
		String poNumber = vals.get("PO NUMBER");
		String total = vals.get("TOTAL");
		total = total.replaceAll("\\,", "");

		Invoice inv = null;
		if (invoiceNumber != null) {

			Project project = projectDao.findByProjectId(projectId);

			Integer poId = null;
			if (poNumber != null) {
				PO po = poDao.findByPoNumber(poNumber);
				poId = po.getId();
			}

			inv = invoiceDao.createInvoiceIfNotExists(invoiceNumber, invoiceDate, billingFrom, billingTo,
					project.getId(), poId, Float.parseFloat(total), invoiceFile);
		}
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			ResourceBean bean = (ResourceBean) iterator.next();
			if (inv != null && bean != null) {
				User user = userDao.findUserByFirstNameAndLastName(bean.getFirstName(), bean.getLastName());
				bean.setId(user.getId());
				invoiceUserDao.createInvoiceUserIfNotExists(inv, bean);
			}

		}

	}

	private ArrayList<ResourceBean> updateUsers(HashMap<String, String> vals) {

		String invoiceNumber = vals.get("INVOICE NUMBER");
		String invoiceDate = vals.get("INVOICE DATE");
		String creditNoteDate = vals.get("CREDIT NOTE DATE");
		String creditNoteNumber = vals.get("CREDIT NOTE NUMBER");
		String orginalInvoice = vals.get("ORIGINAL INVOICE");
		String projectId = vals.get("PROJECT ID");
		String projectName = vals.get("PROJECT NAME");
		String billingFrom = vals.get("BILLING_FROM");
		String billingTo = vals.get("BILLING_TO");
		String poNumber = vals.get("PO NUMBER");
		String total = vals.get("TOTAL");
		String resourceCount = vals.get("RESOURCE_COUNT");
		ArrayList<ResourceBean> resources = new ArrayList<ResourceBean>();
		if (resourceCount != null && Integer.parseInt(resourceCount) > 0) {
			ResourceBean bean = null;
			int count = Integer.parseInt(resourceCount);
			for (int i = 0; i < count; i++) {
				String resource = vals.get("RESOURCE_" + i).toString();
				// System.out.println(resource);
				if (resource.matches(".*Offshore.efforts.*")) {
					continue;
				}
				String[] splits = resource.split("\\|");

				String name = "";
				int namedone = 0;
				for (int j = 0; j < splits.length; j++) {
					if (namedone == 0) {
						if (splits[j].equalsIgnoreCase("offshore") || splits[j].equalsIgnoreCase("onsite")) {
							namedone = j;
							break;
						}
						name = name + splits[j] + " ";
					}

				}
				name = name.trim();
				String lastname = name.split(",")[0];
				String firstname = "";
				if (name.split(",").length > 1) {
					firstname = name.split(",")[1];
				}
				float hours = 0;
				if (splits[namedone + 2].equalsIgnoreCase("MHR")) {
					namedone = namedone - 1;
				}
				hours = Float.parseFloat(splits[namedone + 2].replaceAll(",", ""));
				;
				float rate = Float.parseFloat(splits[namedone + 4].replaceAll(",", "").replaceAll("\\$", ""));

				float totalCost = Float.parseFloat(splits[namedone + 5].replaceAll(",", "").replaceAll("\\$", ""));

				System.out.println(lastname + "," + firstname);

				userDao.createUserIfNotExists(firstname, lastname);
				bean = new ResourceBean();
				bean.setFirstName(firstname);
				bean.setLastName(lastname);
				bean.setBillingRate(rate);
				bean.setHours(hours);
				bean.setTotal(totalCost);
				resources.add(bean);

			}

		}
		return resources;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PODao getPoDao() {
		return poDao;
	}

	public void setPoDao(PODao poDao) {
		this.poDao = poDao;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MinionApplication.class);
		app.run(args);
	}
}

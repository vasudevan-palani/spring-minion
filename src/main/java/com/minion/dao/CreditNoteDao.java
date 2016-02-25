package com.minion.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Constants;
import com.minion.model.CreditNote;
import com.minion.repo.CreditNoteRepository;
import com.minion.repo.InvoiceRepository;
import com.minion.repo.PORepository;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class CreditNoteDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PORepository poRepo;

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private CreditNoteRepository creditNoteRepo;

	public CreditNoteRepository getCreditNoteRepo() {
		return creditNoteRepo;
	}

	public void setCreditNoteRepo(CreditNoteRepository creditNoteRepo) {
		this.creditNoteRepo = creditNoteRepo;
	}

	public InvoiceRepository getInvoiceRepo() {
		return invoiceRepo;
	}

	public void setInvoiceRepo(InvoiceRepository invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

	public UserRepository getuserRepo() {
		return userRepo;
	}

	public void setuserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public StatusRepository getStatusRepo() {
		return statusRepo;
	}

	public void setStatusRepo(StatusRepository statusRepo) {
		this.statusRepo = statusRepo;
	}

	public PORepository getPoRepo() {
		return poRepo;
	}

	public void setPoRepo(PORepository poRepo) {
		this.poRepo = poRepo;
	}

	public CreditNote createCreditNoteIfNotExists(String creditNoteId, String startDate, String endDate,
			Integer projectId, Integer poId, Float total, String originalInvoiceId, String creditNoteFile,
			String creditNoteDate) throws ParseException {

		CreditNote creditNote = creditNoteRepo.findByCreditNoteId(creditNoteId);
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMMMM-yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("d-MMM-yyyy");
		SimpleDateFormat startDateformatter = null;
		SimpleDateFormat endDateformatter = null;
		SimpleDateFormat creditNoteDateformatter = null;

		if (creditNote != null) {
			System.out.println("CreditNote Exists : " + creditNoteId);
			return creditNote;
		} else {

			creditNote = new CreditNote();
			creditNote.setCreditNoteId(creditNoteId);

			if (startDate.matches("\\d.*")) {
				startDateformatter = formatter2;
			} else {
				startDateformatter = formatter1;
			}
			if (endDate != null && endDate.matches("\\d.*")) {
				endDateformatter = formatter2;
			} else {
				endDateformatter = formatter1;
			}
			if (creditNoteDate != null && creditNoteDate.matches("\\d.*")) {
				creditNoteDateformatter = formatter2;
			} else {
				creditNoteDateformatter = formatter1;
			}
			if (startDate != null) {
				System.out.println(startDate);
				creditNote.setStart_date(startDateformatter.parse(startDate));
			}

			if (endDate != null)
				creditNote.setEnd_date(endDateformatter.parse(endDate));
			if (creditNoteDate != null) {
				System.out.println(creditNoteDate);

				creditNote.setCreditNoteDate(creditNoteDateformatter.parse(creditNoteDate));
			}
			creditNote.setPoId(poId);
			creditNote.setProjectId(projectId);
			creditNote.setTotal(total + "");
			creditNote.setCreditNoteFile(creditNoteFile);
			if(originalInvoiceId != null){
				creditNote.setOriginalInvoiceId(originalInvoiceId.trim().split(" ")[0]);
			}
			creditNote.setStatus(statusRepo.findByName(Constants.NEW_STATUS).getId());
			creditNoteRepo.save(creditNote);
			System.out.println("CreditNote Created : " + creditNote.getId() + "," + creditNoteId);
			return creditNote;
		}

	}

}

package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.CreditNote;

public interface CreditNoteRepository extends CrudRepository<CreditNote, Serializable >{

	CreditNote findByCreditNoteId(String creditNoteId);
	
}

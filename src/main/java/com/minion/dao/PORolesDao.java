package com.minion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.POBean;
import com.minion.model.PO;
import com.minion.model.PORoles;
import com.minion.repo.PORepository;
import com.minion.repo.PORolesRepository;

@Component
public class PORolesDao {

	@Autowired
	private PORepository poRepo;

	@Autowired
	private PORolesRepository poRolesRepo;

	
	public PORolesRepository getPoRolesRepo() {
		return poRolesRepo;
	}

	public void setPoRolesRepo(PORolesRepository poRolesRepo) {
		this.poRolesRepo = poRolesRepo;
	}

	public PORepository getPoRepo() {
		return poRepo;
	}

	public void setPoRepo(PORepository poRepo) {
		this.poRepo = poRepo;
	}

	public PORoles createPORolesIfNotExists(POBean pobean) {

		PO po = poRepo.findByPoNumberAndPoVersion(pobean.getPoNumber(),pobean.getPoRevision());
		
		PORoles poRoles = poRolesRepo.findByPoIdAndRolesDescription(po.getId(), pobean.getResourceDesc());
				

		if (poRoles != null) {
			System.out.println("PORoles Exists : " + poRoles.getId());
			return poRoles;
		} else {

			poRoles = new PORoles();
			poRoles.setPoId(po.getId());
			poRoles.setQuantity(pobean.getQuantity());
			poRoles.setRate(pobean.getRate());
			poRoles.setRolesDescription(pobean.getResourceDesc());
			poRoles.setTotal(pobean.getTotal());
			poRolesRepo.save(poRoles);
			System.out.println("PO Roles Created : " + poRoles.getId());
			return poRoles;
		}

	}

}

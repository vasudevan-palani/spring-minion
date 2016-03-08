package com.minion.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.POBean;
import com.minion.model.PO;
import com.minion.model.Project;
import com.minion.repo.PORepository;
import com.minion.repo.ProjectRepository;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class PODao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PORepository poRepo;
	
	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private ProjectRepository projectRepo;

	
	
	public ProjectRepository getProjectRepo() {
		return projectRepo;
	}

	public void setProjectRepo(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
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

	public Integer createPOIfNotExists(String po_number) {

		PO po = this.findByPoNumber(po_number);

		if (po != null) {
			System.out.println("PO Exists : " + po_number);
			return po.getId();
		} else {
			po = new PO();
			po.setPoNumber(po_number);
			poRepo.save(po);
			System.out.println("PO Created : " + po.getId() + "," + po_number);
			return po.getId();
		}

	}

	public PO findByPoNumber(String poNumber) {
		
		List<PO> pos = poRepo.findByPoNumber(poNumber);
		if(pos != null && pos.size() > 0){
			return pos.get(0);
		}
		return null;
	}

	public void updatePO(POBean poBean) {
		PO po = poRepo.findByPoNumberAndPoVersion(poBean.getPoNumber(),poBean.getPoRevision());
		
		if(po == null){
			po = new PO();
			po.setPoNumber(poBean.getPoNumber());
			po.setPoVersion(poBean.getPoRevision());
		}
		if(po != null){
			po.setPoVersion(poBean.getPoRevision());
			
			Project proj = projectRepo.findByProjectId(poBean.getProjectId());
			if(proj != null){
				po.setProjectId(proj.getId());
			}
			
			po.setRequester(poBean.getRequestor());
			po.setTotal(Math.round(poBean.getTotal()));
		}
		poRepo.save(po);
		
	}

}

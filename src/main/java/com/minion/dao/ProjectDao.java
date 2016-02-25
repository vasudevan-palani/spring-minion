package com.minion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.model.Project;
import com.minion.repo.PORepository;
import com.minion.repo.ProjectRepository;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class ProjectDao {

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

	public Integer createProjectIfNotExists(String projectId, String projectName) {

		Project project = projectRepo.findByProjectId(projectId);

		if (project != null) {
			System.out.println("Project Exists : " + projectId);
			return project.getId();
		} else {

			project = new Project();
			project.setProjectId(projectId);
			project.setName(projectName);
			projectRepo.save(project);
			System.out.println("Project Created : " + project.getId() + "," + projectId);
			return project.getId();
		}

	}
	
	public Project findByProjectId(String projectId){
		return projectRepo.findByProjectId(projectId);
	}

}

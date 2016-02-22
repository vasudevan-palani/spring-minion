package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Serializable >{

	Project findByProjectId(String projectId);
	
}

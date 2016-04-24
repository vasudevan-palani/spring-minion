package com.minion.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_project_allocations")
public class UserProjectAllocation {

	@Id
	private Integer id;
	
	@Column(name = "emp_id")
	private Integer empId;

	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "project_number")
	private String projectESAId;

	@Column(name = "name")
	private String projectName;
	
	@Column(name = "po_role_id")
	private String poRoleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectESAId() {
		return projectESAId;
	}

	public void setProjectESAId(String projectESAId) {
		this.projectESAId = projectESAId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPoRoleId() {
		return poRoleId;
	}

	public void setPoRoleId(String poRoleId) {
		this.poRoleId = poRoleId;
	}
	
	
}
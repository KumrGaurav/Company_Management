package com.models;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectID;
	private String projectName;
	private Company companyId;

	private User id;

	private String userName;
	

	private int status;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(int projectID, String projectName, User id, int status, String userName) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.id = id;
		this.status = status;
		this.userName = userName;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", projectName=" + projectName + ", userID=" + id + ", status="
				+ status + "userName=" + userName + "]";
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

}
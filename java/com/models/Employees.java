package com.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Employees {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeID;
	private Project projectID;
	private String projectName; // New field for project name
	private Tasks taskID;
	private String taskName;
	private User userID;
	private String employeeName;
	private Company companyID;
	private String companyName;
	private Integer activeFlag;
	private Date createTime;

	public Employees() {
		super();
	}

	public Employees(Integer employeeID, Project projectID, String projectName, Tasks taskID, String taskName,
			User userID, String employeeName, Company companyID, String companyName, Integer activeFlag,
			Date createTime) {
		super();
		this.employeeID = employeeID;
		this.projectID = projectID;
		this.projectName = projectName;
		this.taskID = taskID;
		this.taskName = taskName;
		this.userID = userID;
		this.employeeName = employeeName;
		this.companyID = companyID;
		this.companyName = companyName;
		this.activeFlag = activeFlag;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Employees [employeeID=" + employeeID + ", projectID=" + projectID + ", projectName=" + projectName
				+ ", taskID=" + taskID + ", taskName=" + taskName + ", userID=" + userID + ", employeeName="
				+ employeeName + ", companyID=" + companyID + ", companyName=" + companyName + ", activeFlag="
				+ activeFlag + ", createTime=" + createTime + "]";
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Project getProjectID() {
		return projectID;
	}

	public void setProjectID(Project projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Tasks getTaskID() {
		return taskID;
	}

	public void setTaskID(Tasks taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Company getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Company companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

package com.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskID;
	private Project projectID;
	private String projectName; // New field for project name
	private String taskName;
	private User id;
	private User createdByUser;
	private User modifiedByUser;

	private String taskStatus;
	private Date createTime;
	private Integer activeFlag;

	private String userName;
	private String createdBy;
	private String modifiedBy;
	private String taskDetails;

	@Override
	public String toString() {
		return "Tasks [taskID=" + taskID + ", projectID=" + projectID + ", taskName=" + taskName + ", id=" + id
				+ ", createdByUser=" + createdByUser + ", modifiedByUser=" + modifiedByUser + ", taskStatus="
				+ taskStatus + ", createTime=" + createTime + ", activeFlag=" + activeFlag + ", projectName="
				+ projectName + ", userName=" + userName + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ "]";
	}

	public Tasks(Integer taskID, Project projectID, String taskName, User id, User createdByUser, User modifiedByUser,
			String taskStatus, Date createTime, Integer activeFlag, String projectName, String userName,
			String createdBy, String modifiedBy,String taskDetails) {
		super();
		this.taskID = taskID;
		this.projectID = projectID;
		this.taskName = taskName;
		this.id = id;
		this.createdByUser = createdByUser;
		this.modifiedByUser = modifiedByUser;
		this.taskStatus = taskStatus;
		this.createTime = createTime;
		this.activeFlag = activeFlag;
		this.projectName = projectName;
		this.userName = userName;
		this.createdBy = createdBy;
		this.taskDetails=taskDetails;
		this.modifiedBy = modifiedBy;
	}

	public Integer getTaskID() {
		return taskID;
	}

	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}

	public Project getProjectID() {
		return projectID;
	}

	public void setProjectID(Project projectID) {
		this.projectID = projectID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public User getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Tasks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails =taskDetails;
	}


}
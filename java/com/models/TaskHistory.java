package com.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TaskHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskHistoryID;
	private Tasks taskId;
	private String taskName;
	private String taskStatus;
	private User performedByID;
	private String performedBy;
	private String performedAct;
	private Date createTime;
	private Integer activeFlag;

	public TaskHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskHistory(Integer taskHistoryID, Tasks taskId, String taskName, String taskStatus, User performedByID,
			String performedBy, String performedAct, Date createTime, Integer activeFlag) {
		super();
		this.taskHistoryID = taskHistoryID;
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.performedByID = performedByID;
		this.performedBy = performedBy;
		this.performedAct = performedAct;
		this.createTime = createTime;
		this.activeFlag = activeFlag;
	}

	@Override
	public String toString() {
		return "TaskHistory [taskHistoryID=" + taskHistoryID + ", taskId=" + taskId + ", taskName=" + taskName
				+ ", taskStatus=" + taskStatus + ", performedByID=" + performedByID + ", performedBy=" + performedBy
				+ ", performedAct=" + performedAct + ", createTime=" + createTime + ", activeFlag=" + activeFlag + "]";
	}

	public Integer getTaskHistoryID() {
		return taskHistoryID;
	}

	public void setTaskHistoryID(Integer taskHistoryID) {
		this.taskHistoryID = taskHistoryID;
	}

	public Tasks getTaskId() {
		return taskId;
	}

	public void setTaskId(Tasks taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public User getPerformedByID() {
		return performedByID;
	}

	public void setPerformedByID(Object currentUserId) {
		this.performedByID = (User) currentUserId;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public String getPerformedAct() {
		return performedAct;
	}

	public void setPerformedAct(String performedAct) {
		this.performedAct = performedAct;
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

}
package com.models;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_attachment")
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer attachmentId;
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "taskID")
	 */
	private Tasks taskId;
	private String taskName;
	private User id;
	private String attachmentName;
	private String attachmentBy;
	private String attachmentFile;
	private Date attachmentDateTime;
	private Integer activeFlag;
	private String userName;
    private byte[] file; // Property to hold the uploaded file
	private User attachmentByUser;



	//@Column(name = "attachment_path")
	private String attachmentPath;

	public Attachment() {
	};

	public Attachment(Integer attachmentId, Tasks taskId, String taskName, User id, String attachmentName,
			String attachmentBy, String attachmentFile, Date attachmentDateTime, Integer activeFlag, String userName,
			String attachmentPath,byte[] file) {
		super();
		this.attachmentId = attachmentId;
		this.taskId = taskId;
		this.taskName = taskName;
		this.id = id;
		this.attachmentName = attachmentName;
		this.attachmentFile = attachmentFile;
		this.attachmentDateTime = attachmentDateTime;
		this.activeFlag = activeFlag;
		this.userName = userName;
		this.attachmentPath = attachmentPath;
		this.attachmentByUser = attachmentByUser;
		this.attachmentBy = attachmentBy;
		this.file = file;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getAttachmentDateTime() {
		return attachmentDateTime;
	}

	public void setAttachmentDateTime(Date attachmentDateTime) {
		this.attachmentDateTime = attachmentDateTime;
	}

	public String getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(String attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Tasks getTaskId() {
		return taskId;
	}

	public void setTaskId(Tasks taskId) {
		this.taskId = taskId;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
    
    // Other methods

    public byte[] getFile() {
    	return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", taskId=" + taskId + ", taskName=" + taskName + ", id="
				+ id + ", attachmentName=" + attachmentName + ", attachmentFile=" + attachmentFile
				+ ", attachmentDateTime=" + attachmentDateTime + ", activeFlag=" + activeFlag + ", userName=" + userName
				+ ", attachmentPath=" + attachmentPath + ", attachmentByUser="+ attachmentByUser + ", attachmentBy=" + attachmentBy +
				", file=" + file+"]";
	}

	public String getAttachmentBy() {
		return attachmentBy;
	}

	public void setAttachmentBy(String attachmentBy) {
		this.attachmentBy = attachmentBy;
	}

	public User getAttachmentByUser() {
		return attachmentByUser;
	}

	public void setAttachmentByUser(User attachmentByUser) {
		this.attachmentByUser = attachmentByUser;
	}

	}
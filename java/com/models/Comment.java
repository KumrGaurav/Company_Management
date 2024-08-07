package com.models;

import java.util.Date;


public class Comment {

	private int commentID;
	private String comment;
	private Date createTime;
	private int status;
	private Tasks task;
	private User user;

	public Comment() {
		super();
	}

	public Comment(int commentID, String comment, Date createTime, int status, Tasks task, User user) {
		super();
		this.commentID = commentID;
		this.comment = comment;
		this.createTime = createTime;
		this.status = status;
		this.task = task;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", comment=" + comment + ", createTime=" + createTime + ", status="
				+ status + ", task=" + task + ", user=" + user + "]";
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Tasks getTask() {
		return task;
	}

	public void setTask(Tasks task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
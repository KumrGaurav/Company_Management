package com.models;
import java.util.Date;
public class CompanyTask {
	private int id;
	private String taskName;
	private String projectName;
	private String employeeName;
	private String companyName;
	private String taskStatus;
	private Date createTime;
	private int status;
	private CompanyUser companyUser;
	private CompanyProject companyProject;
	
	public CompanyTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyTask(int id, String taskName, String projectName, String employeeName, String companyName,
			String taskStatus, Date createTime, int status, CompanyUser companyUser, CompanyProject companyProject) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.projectName = projectName;
		this.employeeName = employeeName;
		this.companyName = companyName;
		this.taskStatus = taskStatus;
		this.createTime = createTime;
		this.status = status;
		this.companyUser = companyUser;
		this.companyProject = companyProject;
	}
	@Override
	public String toString() {
		return "CompanyTask [id=" + id + ", taskName=" + taskName + ", projectName=" + projectName + ", employeeName="
				+ employeeName + ", companyName=" + companyName + ", taskStatus=" + taskStatus + ", createTime="
				+ createTime + ", status=" + status + ", companyUser=" + companyUser + ", companyProject="
				+ companyProject + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public CompanyUser getCompanyUser() {
		return companyUser;
	}
	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}
	public CompanyProject getCompanyProject() {
		return companyProject;
	}
	public void setCompanyProject(CompanyProject companyProject) {
		this.companyProject = companyProject;
	}
	
	
	
}

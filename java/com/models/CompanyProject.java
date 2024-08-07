package com.models;
import javax.persistence.Column;
public class CompanyProject {
	@Column(name="S.No")
	int id;
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="project_name")
	private String projectName; 
	
	@Column(name="user_name")
	private String UserName;
	
	@Column(name="status")
	private int status;
	
	@Column(name="company_id")
	private Company company;
	
	@Column(name="employee_id")
	private CompanyUser companyUser;

	public CompanyProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyProject(int id, String companyName, String projectName, String userName, int status, Company company,
			CompanyUser companyUser) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.projectName = projectName;
		UserName = userName;
		this.status = status;
		this.company = company;
		this.companyUser = companyUser;
	}

	@Override
	public String toString() {
		return "CompanyProject [id=" + id + ", companyName=" + companyName + ", projectName=" + projectName
				+ ", UserName=" + UserName + ", status=" + status + ", company=" + company + ", companyUser="
				+ companyUser + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(CompanyUser companyUser) {
		this.companyUser = companyUser;
	}



}

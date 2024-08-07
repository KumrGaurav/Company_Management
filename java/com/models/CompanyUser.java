package com.models;

public class CompanyUser {
	private int id;
	private String employeeName;
	private String CompanyName;
	private Company company;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CompanyUser() {
		super();
		// TODO Auto-generated constructor stubs
	}
	public CompanyUser(String employeeName, String companyName, Company company) {
		super();
		this.employeeName = employeeName;
		CompanyName = companyName;
		this.company = company;
	}
	@Override
	public String toString() {
		return "CompanyUser [employeeName=" + employeeName + ", CompanyName=" + CompanyName + ", company=" + company
				+ "]";
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
}

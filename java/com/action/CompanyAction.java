
package com.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.action.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dao.CompanyDAO;
import com.dao.UserDao;
import com.dao.projectDAO;
import com.models.Company;
import com.models.User;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CompanyAction extends ActionSupport implements ServletRequestAware {
	private CompanyDAO CompanyDAO;
	private Company company;
	private int id;
	private User userID;
	private String userName;
	private int flag;
	User user;
	private List<Company> companies;
	private HttpServletRequest request;
	private Map<String, Object> session;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() {
		final CompanyDAO CompanyDAO = new CompanyDAO();
		companies = CompanyDAO.listCompanies();
//		for (final Company company : companies) {
//			company.setUserName(company.getUserID().getFirstName());
//		}

		return SUCCESS;
	}

	public String save() {
		try {

			final String companyName = request.getParameter("companyName");
			System.out.println("companyName :" + companyName);

			final String companyUserFirstName = request.getParameter("companyUserFirstName");
			System.out.println("companyUserFirstName :" + companyUserFirstName);

			final String companyUserLastName = request.getParameter("companyUserLastName");
			System.out.println("companyUserLastName :" + companyUserLastName);

			final String companyUserPassword = request.getParameter("password");
			System.out.println("companyUserPassword :" + companyUserPassword);

			final String companyUserEmail = request.getParameter("email");
			System.out.println("companyUserEmail :" + companyUserEmail);

			company = new Company();

			user = new User();

			company.setCompanyName(companyName);
			user.setFirstName(companyUserFirstName);
			user.setLastName(companyUserLastName);
			user.setPassword(companyUserPassword);
			user.setRole("Company User");
			user.setEmail(companyUserEmail);
			user.setCompanyId(company);
			user.setActiveFlag(1);
			CompanyDAO companyDAO = new CompanyDAO();
			companyDAO.addCompany(company);

			UserDao userDAO = new UserDao();
			userDAO.addUser(user);

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			final HttpSession session = request.getSession();

			final String id = request.getParameter("companyId");
			System.out.println("id to delete: " + id);
			user = new User();

			final int companyId = Integer.parseInt(id);

			final CompanyDAO companyDAO = new CompanyDAO();
			User user1 = (User) session.getAttribute("user");
			user.setModifiedAt(new Date());
			//user.setModifiedBy(modifiedByID);

			companyDAO.deleteCompany(companyId,user1);
			user.setActiveFlag(0);

		} catch (final Exception e) {

			System.out.println(e.getMessage());
			return ERROR; 

		}
		return SUCCESS;
	}

	public String update() {
		try {
			
			CompanyDAO = new CompanyDAO();
			company = new Company();
			user = new User();
			final HttpSession session = request.getSession();

			final String id = request.getParameter("companyId");
			final String companyName = request.getParameter("companyName");
			int modifiedByID = (int) session.getAttribute("userid");
			final String companyUserFirstName = request.getParameter("companyUserFirstName");
			final String companyUserLastName = request.getParameter("companyUserLastName");
			final String companyUserPassword = request.getParameter("password");
			final String companyUserEmail = request.getParameter("email");

			User user1 = (User) session.getAttribute("user");
			int companyId = Integer.parseInt(id);
			company.setCompanyName(companyName);
			company.setModifiedAt(new Date());

			user.setFirstName(companyUserFirstName);
			user.setLastName(companyUserLastName);
			user.setPassword(companyUserPassword);
			user.setEmail(companyUserEmail);

			CompanyDAO.updateCompany(companyId, companyName, modifiedByID, companyUserFirstName, companyUserLastName,
					companyUserPassword, companyUserEmail, user1);

			return SUCCESS;

		} catch (final Exception e) {
			System.out.println("Error updating company: " + e.getMessage());
			return ERROR;
		}
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public void setCompanyDAO(CompanyDAO CompanyDAO) {
		this.CompanyDAO = CompanyDAO;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public String addCompany(Company company) {
		final CompanyDAO CompanyDAO = new CompanyDAO();

		CompanyDAO.addCompany(company);
		return SUCCESS;
	}

	public String listCompanies() {
		companies = CompanyDAO.listCompanies();
		for (final Company company : companies) {
			company.setUserName(company.getId().getFirstName());
		}
		return SUCCESS;
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

}
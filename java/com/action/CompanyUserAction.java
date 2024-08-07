package com.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.action.ServletRequestAware;

import com.dao.CompanyDAO;
import com.dao.CompanyUserDAO;
import com.dao.UserDao;
import com.models.Company;
import com.models.Project;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

public class CompanyUserAction extends ActionSupport implements ServletRequestAware {
	private List<Project> projects;
	private CompanyUserDAO companyUserDAO;
	// private int companyId;
	private HttpServletRequest request;
	HttpSession session;
	User user;
	/*
	 * private UserDao userDAO;
	 */ private List<User> employees;
	private int userId;
	 private List<User> allEmployees;


	public CompanyUserAction() {

	}

	@Override
	public String execute() {
		companyUserDAO = new CompanyUserDAO();
		//projects = companyUserDAO.listCompanyProjects();
		return SUCCESS;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public String listProjects() {
		try {
			companyUserDAO = new CompanyUserDAO();
			session = request.getSession();
			User user1 = (User) session.getAttribute("user");
			int companyId = user1.getCompanyId().getCompanyId();
			projects = companyUserDAO.listCompanyProjects(companyId);
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ERROR;
		}
		
	}

	public String listEmployees() {
		companyUserDAO = new CompanyUserDAO();
		session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		int companyId = user1.getCompanyId().getCompanyId();
		employees = companyUserDAO.listCompanyEmployees(companyId);
		return SUCCESS;
	}

	public List<User> getEmployees() {
		return employees;
	}

	public String addEmployee() {
		try {
			final String employeeFirstName = request.getParameter("employeeFirstName");
			System.out.println("employeeFirstName :" + employeeFirstName);

			final String employeeLastName = request.getParameter("employeeLastName");
			System.out.println("employeeLastName :" + employeeLastName);

			final String employeePassword = request.getParameter("password");
			System.out.println("employeePassword :" + employeePassword);

			final String employeeEmail = request.getParameter("email");
			System.out.println("employeeEmail :" + employeeEmail);

			session = request.getSession();
			User user1 = (User) session.getAttribute("user");
			int companyId = user1.getCompanyId().getCompanyId();

			user = new User();

			user.setFirstName(employeeFirstName);
			user.setLastName(employeeLastName);
			user.setPassword(employeePassword);
			user.setRole("employee");
			user.setEmail(employeeEmail);

			Company company = new Company();
			company.setCompanyId(companyId);
			user.setCompanyId(company);

			user.setActiveFlag(1);
			CompanyUserDAO companyUserDAO = new CompanyUserDAO();
			companyUserDAO.addEmployee(user);

			/*
			 * UserDao userDAO = new UserDao(); userDAO.addUser(user);
			 */
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	public String deleteEmployee() {
		try {
			final String id = request.getParameter("employeeId");
			System.out.println("id to delete: " + id);
			user = new User();
			session = request.getSession();

			// Convert id to int if necessary
			final int employeeId = Integer.parseInt(id);

			// Instantiate companyDAO and delete the company
			final CompanyUserDAO companyUserDAO = new CompanyUserDAO();
			int modifiedByID = (int) session.getAttribute("id");
			user.setModifiedAt(new Date());
			user.setModifiedBy(modifiedByID);

			companyUserDAO.deleteEmployee(employeeId,modifiedByID);
			// user.setActiveFlag(0);

		} catch (final Exception e) {

			System.out.println(e.getMessage());
			return ERROR; // Return error status if any exception occurs

		}
		return SUCCESS; // Return success status if the delete operation is successful

	}

	public String updateEmployee() {
		try {

			companyUserDAO=new CompanyUserDAO();
			user = new User();
			final HttpSession session = request.getSession();

			final String id = request.getParameter("employeeId");
			final int employeeId = Integer.parseInt(id);
			final String companyUserFirstName = request.getParameter("employeeFirstName");
			final String companyUserLastName = request.getParameter("employeeLastName");
			final String companyUserPassword = request.getParameter("password");
			final String companyUserEmail = request.getParameter("email");
			int modifiedByID = (int) session.getAttribute("userid");

			user.setModifiedAt(new Date());
			user.setFirstName(companyUserFirstName);
			user.setLastName(companyUserLastName);
			user.setPassword(companyUserPassword);
			user.setEmail(companyUserEmail);
			user.setRole("employee");
			user.setModifiedBy(modifiedByID);

			companyUserDAO.updateEmployee(employeeId,companyUserFirstName, companyUserLastName,
					companyUserPassword, companyUserEmail,modifiedByID);

			return SUCCESS;

		} catch (final Exception e) {
			System.out.println("Error updating employee: " + e.getMessage());
			return ERROR;
		}
	}
	
	public String listAllEmployees()
	{
		try {
			companyUserDAO = new CompanyUserDAO();
			employees = companyUserDAO.listAllEmployees();
			return SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ERROR;
		}
	}
	public List<User> getAllEmployees() {
		return employees;
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}
}
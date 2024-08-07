package com.action;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.dao.CompanyUserDAO;
import com.dao.UserDao;
import com.models.Project;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private HttpServletRequest request;
	private Map<String, Object> session;
	private List<User> users;
	private UserDao userDao;
	private User user;
	private int id;
	private String dashboard;
	private String password;
	private String firstName;
	private String lastName;
	private String email;

	CompanyUserDAO companyUserDAO = new CompanyUserDAO();

	public UserAction() {
	}

	@Override
	public String execute() {
		 HttpSession session = ServletActionContext.getRequest().getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	       return SUCCESS;
	}

	public String authentication() {
		final String userName = request.getParameter("userName");
		final String password = request.getParameter("password");

		final UserDao userDao = new UserDao();
		final User user = userDao.getUserDetails(userName, password);

		if (user != null) {
			if (user.getRole().equals("Company User")) {
				dashboard = "companyUserDashboard";
				session.put("employeeName", user.getFirstName() + " " + user.getLastName());
				session.put("currentUser", user.getFirstName());
				session.put("role", user.getRole());
				session.put("id", user.getId());
			} else if (user.getRole().equals("employee")) {
				dashboard = "employees";
				System.out.println(dashboard);
				session.put("employeeName", user.getFirstName() + " " + user.getLastName());
				session.put("currentUser", user.getFirstName());
				session.put("id", user.getId());
				session.put("role", user.getRole());
				session.put("loginUser", user);
			} else if (user.getRole().equalsIgnoreCase("admin")) {
				dashboard = "admin";
				session.put("admin", user.getFirstName() + " " + user.getLastName());
				session.put("currentUser", user.getFirstName());
				session.put("id", user.getId());
				session.put("role", user.getRole());
				System.out.println(dashboard);
				session.put("employeeName", user.getFirstName() + " " + user.getLastName());
				session.put("currentUser", user.getFirstName());
				session.put("id", user.getId());

			} else {
				return "error";
			}
		}
		session.put("dashboard", dashboard);
		session.put("userid", user.getId());
		session.put("username", user.getFirstName());
		session.put("user", user);
		return SUCCESS;
	}

	public String save() {
		try {
			final int id = Integer.parseInt(request.getParameter("id"));
			final String password = request.getParameter("password");

			user = new User();
			user.setId(id);
			user.setPassword(password);
			user.setActiveFlag(1);
			final UserDao userDao = new UserDao();
			userDao.addUser(user);

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getAll() {
		final UserDao userDao = new UserDao();
		users = userDao.getAllUsers();
		return "success";
	}

	public String addUser() {
		final UserDao userDao = new UserDao();
		userDao.addUser(user);
		return "success";
	}

	public String deleteUser() {
		userDao.deleteUser(id);
		return "success";
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void withServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

}
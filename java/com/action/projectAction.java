package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.UserDao;

import com.dao.AttachmentDAO;
import com.dao.EmployeeProjectDAO;
import com.dao.projectDAO;
import com.models.EmployeeProject;
import com.models.Project;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

public class projectAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private List<Project> projects;
	private List<Project> users;
	private projectDAO projectDao;
	private Project project;

	@Override
	public String execute() {
		return "success";
	}

	public String save() {
		 try {
	            project = new Project();
	            projectDAO projectDao = new projectDAO();

	            String projectName = request.getParameter("projectName");

	            final String assignedTo = request.getParameter("assignedTo");
	            final int assignedToId = projectDAO.getUserIDByName(assignedTo);
	            final User user = new User();
	            user.setId(assignedToId);
	            project.setId(user);
	            project.setProjectName(projectName);
	            projectDao.addProject(project);

	            // Get the newly inserted project ID
	            int projectId = project.getProjectID();

	            // Create an instance of EmployeeProject
	            EmployeeProject employeeProject = new EmployeeProject();
	            employeeProject.setUser(user);
	            employeeProject.setProject(project);

	            // Save the EmployeeProject instance
	            EmployeeProjectDAO employeeProjectDao = new EmployeeProjectDAO();
	            employeeProjectDao.addEmployeeProject(employeeProject);

	        } catch (final Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return SUCCESS;
		/*
		 * try { project = new Project(); projectDAO projectDao = new projectDAO();
		 * 
		 * // int projectID = Integer.parseInt(request.getParameter("projectID"));
		 * String projectName = request.getParameter("projectName");
		 * 
		 * // int userID = Integer.parseInt(request.getParameter("userID")); final
		 * String assignedTo = request.getParameter("assignedTo"); final int
		 * assignedToId = projectDAO.getUserIDByName(assignedTo); final User user = new
		 * User(); user.setId(assignedToId); project.setId(user); //
		 * project.setProjectID(projectID); project.setProjectName(projectName);
		 * projectDao.addProject(project);
		 * 
		 * } catch (final Exception e) { System.out.println(e.getMessage()); } return
		 * SUCCESS;
		 */
	}

	public String updateProject() {
		try {
			
			String projectName = request.getParameter("projectName");
			
			String assignedToUsername = request.getParameter("assignedTo");
			int assignedToID = projectDAO.getUserIDByName(assignedToUsername);
			User user=new User();
			user.setId(assignedToID);
			project.setId(user);

			//System.out.println("Id of the project to update: " + projectID);

			project = new Project();
			project.setStatus(1);
			//project.setProjectID(projectID);
			project.setProjectName(projectName);
			//project.setId(userID);
			//project.setUserName(userName);

			final projectDAO projectDao = new projectDAO();
			projectDao.updateProject(project);

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	public String getAll() {
		try {
			System.out.println("Hello");
			projectDao = new projectDAO();
			projects = projectDao.getAllProjects();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";

	}

	public String deleteProject() {
		try {
			final int projectID = Integer.parseInt(request.getParameter("projectID"));
			System.out.println("id to delete: " + projectID);

			final projectDAO projectDao = new projectDAO();
			projectDao.deleteProject(projectID);

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}

	public String getAllUsers() {
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		final projectDAO projectDao = new projectDAO();
		users = projectDao.getAllProjectUsers(projectID);
		return "success";
	}
	
	public projectAction() {
		super();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public projectDAO getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(projectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
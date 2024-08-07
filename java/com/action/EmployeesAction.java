
package com.action;

import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletRequestAware;
//import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.action.SessionAware;

import com.dao.CompanyUserDAO;
import com.dao.EmployeesDAO;
import com.models.Company;
import com.models.Project;
import com.models.Tasks;
import com.models.User;
import com.mysql.cj.xdevapi.SessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeesAction extends ActionSupport implements ServletRequestAware, SessionAware {
	// private Integer employeeID;
	private Project projectID;
	// private int prId;
	private String projectName; // New field for project name
	private Tasks taskID;
	private String taskName;
	private User userID;
	private String employeeName;
	private Company companyID;
	private String companyName;

	private EmployeesDAO employeesDAO;
	// private List<Employees> employees;
	private List<Tasks> employeeData;
	// private Map<String, Object> session;
	HttpSession session1;
	private List<Project> employeeProjects;
	private SessionFactory sessionFactory;
	private HttpServletRequest request;
	public List<Project> projects;
	public List<Tasks> tasks;

	private File file;
	private String fileContentType;
	private String fileFileName;

	@Override
	public String execute() {

		employeesDAO = new EmployeesDAO();
		// List<Tasks> employee = null;

		// Retrieve user ID from the session
		final HttpSession session = request.getSession();
		final Integer userID = (Integer) session.getAttribute("id");

		if (userID != null) {
			employeeData = employeesDAO.getLoggedInEmployeeTask(userID);

			// Add project names to the tasks list
			for (final Tasks employees : employeeData) {
				employees.setUserName(employees.getId().getFirstName());

				final String projectName = employeesDAO.getProjectNameFromTask(employees.getProjectID().getProjectID());
				employees.setProjectName(projectName);

			}

			// Get employee's projects
			// employeeProjects = employeesDAO.getLoggedInEmployeeProjects(userID);

			return SUCCESS;
		} else {
			addActionError("User is not logged in.");
			return ERROR;
		}
	}

	public String listProjects() {
		try {
			employeesDAO = new EmployeesDAO();
			session1 = request.getSession();
			// User user1 = (User) session1.getAttribute("user");
			// int employeeId = user1.getCompanyId().getCompanyId();
			int employeeId = (int) session1.getAttribute("id");
			// int projectId = Integer.parseInt(request.getParameter("projectID"));
			projects = employeesDAO.listEmployeeProjects(employeeId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	public String listProjectTask() {
		try {
			employeesDAO = new EmployeesDAO();
			session1 = request.getSession();
			// User user1 = (User) session1.getAttribute("user");
			// int employeeId = user1.getCompanyId().getCompanyId();
			int employeeId = (int) session1.getAttribute("id");
			Integer projectId = Integer.parseInt(request.getParameter("projectId"));

			// Project projectId=getProjectID();
			tasks = employeesDAO.listEmployeeTasks(employeeId, projectId);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public Integer getActiveFlag() {
//		return activeFlag;
//	}
//
//	public void setActiveFlag(Integer activeFlag) {
//		this.activeFlag = activeFlag;
//	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

//	public List<Employees> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employees> employees) {
//		this.employees = employees;
//	}

	public List<Tasks> getEmployeeData() {
		return employeeData;
	}

	public void setEmployeeData(List<Tasks> employeeData) {
		this.employeeData = employeeData;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

//	public HttpServletRequest getRequest() {
//		return request;
//	}
//
//	public void setRequest(HttpServletRequest request) {
//		this.request = request;
//	}

//	public Integer getEmployeeID() {
//		return employeeID;
//	}
//
//	public void setEmployeeID(Integer employeeID) {
//		this.employeeID = employeeID;
//	}

	public Project getProjectID() {
		return projectID;
	}

	public void setProjectID(Project projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Tasks getTaskID() {
		return taskID;
	}

	public void setTaskID(Tasks taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Company getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Company companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub

	}

	/*
	 * public Map<String, Object> getSession() { return session; }
	 */
	@Override
	public void withSession(Map<String, Object> session) {
		// TODO Auto-generated method stub

	}

	public EmployeesDAO getEmployeesDAO() {
		return employeesDAO;
	}

	public void setEmployeesDAO(EmployeesDAO employeesDAO) {
		this.employeesDAO = employeesDAO;
	}

	public List<Project> getEmployeeProjects() {
		return employeeProjects;
	}

	public void setEmployeeProjects(List<Project> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String importFile() throws Exception  {
		/*destPath = "C:/apache-tomcat-6.0.33/work/";
		File destFile = new File(destPath, filefile);
		FileUtils.copyFile(file, destFile);*/
		//String filePath = ServletActionContext.getServletContext().getRealPath("A:/uploads") + "/" + getFileFileName();
		String filePath = "A:/uploads/" + getFileFileName();
        File destFile = new File(filePath);
        
        // Move the uploaded file to the desired location
        FileUtils.copyFile(file, destFile);
        
        // Now you have the path of the uploaded file in 'filePath'
        System.out.println("Uploaded file path: " + filePath);
		
		
		
		
		String excelFilePath = filePath;

		try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {
			// Get the first sheet
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			// Skip header row
			if (iterator.hasNext()) {
				iterator.next();
			}

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				// Assuming columns: Column1 (String), Column2 (Double), Column3 (String)
				String column1 = currentRow.getCell(0).getStringCellValue();
				String column2 = currentRow.getCell(1).getStringCellValue();
				String column3 = currentRow.getCell(2).getStringCellValue();
				String column4 = currentRow.getCell(2).getStringCellValue();

				// Create and persist your Hibernate entity object using the data read from
				// Excel
				User entity = new User();
				entity.setFirstName(column1);
				entity.setLastName(column2);
				entity.setEmail(column3);
				entity.setPassword(column4);
				entity.setRole("employee");
				entity.setActiveFlag(1);

				session1 = request.getSession();
				User user1 = (User) session1.getAttribute("user");
				int companyId = user1.getCompanyId().getCompanyId();
				Company company = new Company();
				company.setCompanyId(companyId);
				entity.setCompanyId(company);

				int modifiedByID = (int) session1.getAttribute("id");
				entity.setModifiedAt(new Date());
				entity.setModifiedBy(modifiedByID);

				employeesDAO = new EmployeesDAO();
				employeesDAO.importFile(entity);

				// Save the entity using Hibernate session
				// Make sure to have a properly configured Hibernate session factory
				// and begin and commit transactions accordingly
				// sessionFactory.getCurrentSession().save(entity);
			}
			/*
			 * CompanyUserAction companyUserAction=new CompanyUserAction();
			 * companyUserAction.listEmployees();
			 */
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return "companyUserEmployees";
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}

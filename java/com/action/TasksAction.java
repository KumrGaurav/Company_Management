package com.action;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.action.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.TasksDAO;
import com.dao.UserDao;
import com.models.Project;
import com.models.TaskHistory;
import com.models.Tasks;
import com.models.User;
import com.mysql.cj.xdevapi.SessionFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TasksAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private HttpServletResponse response;
	private TasksDAO taskDAO;
	private Tasks task;
	private User user;
	private Integer taskID;
	private Long projectID;
	private String taskName;
	private Integer id;
	private String userName;
	private String createdBy;
	private String modifiedBy;
	private String taskStatus;
	private Date createTime;
	private List<Tasks> tasks;
	private SessionFactory sessionFactory;
	private List<User> users;

	private HttpServletRequest request;

	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String projectTask() {
	    int projectID = Integer.parseInt(request.getParameter("projectID"));
	    String projectName = request.getParameter("projectName");

	    taskDAO = new TasksDAO();
	    tasks = taskDAO.getProjectTask(projectID);

	    final UserDao userDao = new UserDao();
	    users = userDao.getAllUsers();

	    // Add project names to the tasks list
	    for (final Tasks task : tasks) {
	        final TasksDAO taskDAO = new TasksDAO();

	        task.setProjectName(projectName);

	        final String userName = taskDAO.getUserName(task.getId().getId());
	        task.setUserName(userName);

	        final String createdBy = taskDAO.getUserName(task.getCreatedByUser().getId());
	        task.setCreatedBy(createdBy);

	        final String modifiedBy = taskDAO.getUserName(task.getModifiedByUser().getId());
	        task.setModifiedBy(modifiedBy);
	    }

	    JSONArray jsonArray = new JSONArray();
	    for (Tasks task : tasks) {
	        JSONObject jsonObject = new JSONObject();
	        try {
	            jsonObject.put("id", task.getTaskID());
	            jsonObject.put("taskName", task.getTaskName());
	            jsonObject.put("userName", task.getUserName());
	            jsonObject.put("createdBy", task.getCreatedBy());
	            jsonObject.put("modifiedBy", task.getModifiedBy());
	            jsonObject.put("taskStatus", task.getTaskStatus());
	            jsonObject.put("createTime", task.getCreateTime());
	            jsonArray.put(jsonObject);
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	    }

	    try {
	        response.setContentType("application/json");
	        response.getWriter().write(jsonArray.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public String addProjectTask() {
		String createTimeParam = request.getParameter("createTime");
		String taskName = request.getParameter("taskName");
		String taskStatus = request.getParameter("taskStatus");
		String assignedToUsername = request.getParameter("userName");
		int projectID = Integer.parseInt(request.getParameter("projectID"));
		String createdByUsername = request.getParameter("currentUser");
		String modifiedByUsername = request.getParameter("currentUser");

		try {
			final String projectName = request.getParameter("projectName");
			task = new Tasks();

			if (projectName != null) {
				final Project project = new Project();
				project.setProjectID(projectID);
				task.setProjectID(project);
			}

			task.setTaskStatus(taskStatus);
			task.setTaskName(taskName);

			if (assignedToUsername != null && !assignedToUsername.isEmpty()) {
				final int assignedToID = TasksDAO.getUserIDByName(assignedToUsername);
				if (assignedToID != -1) {
					final User user = new User();
					user.setId(assignedToID);
					task.setId(user);
				}
			}

			if (createTimeParam != null) {
				try {
					final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					final Date createTime = dateFormat.parse(createTimeParam);
					task.setCreateTime(createTime);
				} catch (final ParseException e) {
					// Handle parsing exception
					e.printStackTrace();
				}
			}

			final HttpSession session = request.getSession();

			final String currentUser = (String) session.getAttribute("currentUser");

			// final String createdByUsername = request.getParameter("createdBy");
			if (currentUser != null) {
				final int createdByID = TasksDAO.getUserIDByName(currentUser);
				if (createdByID != -1) {
					final User createdByUser = new User();
					createdByUser.setId(createdByID);
					task.setCreatedByUser(createdByUser);
				}
			}

			// final String modifiedByUsername = request.getParameter("modifiedBy");
			if (currentUser != null) {
				final int modifiedByID = TasksDAO.getUserIDByName(currentUser);
				if (modifiedByID != -1) {
					final User modifiedByUser = new User();
					modifiedByUser.setId(modifiedByID);
					task.setModifiedByUser(modifiedByUser);
				}
			}

			TasksDAO taskDAO = new TasksDAO();
			taskDAO.addTask(task, assignedToUsername);
			saveTaskHistory(task, "Added");

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}

	@Override
	public String execute() {
		taskDAO = new TasksDAO();
		tasks = taskDAO.getAllTasks();

		// Add project names to the tasks list
		for (final Tasks task : tasks) {
			final TasksDAO taskDAO = new TasksDAO();
			final String projectName = taskDAO.getProjectName(task.getProjectID().getProjectID());
			task.setProjectName(projectName);

			final String userName = taskDAO.getUserName(task.getId().getId());
			task.setUserName(userName);

			final String createdBy = taskDAO.getUserName(task.getCreatedByUser().getId());
			task.setCreatedBy(createdBy);

			final String modifiedBy = taskDAO.getUserName(task.getModifiedByUser().getId());
			task.setModifiedBy(modifiedBy);
		}

		return SUCCESS;
	}

	public String save() {

		try {
			task = new Tasks();
			final String taskIDParam = request.getParameter("taskID");
			if (taskIDParam != null) {
				task.setTaskID(Integer.parseInt(taskIDParam));
			}
			final String projectName = request.getParameter("projectName");
			if (projectName != null) {
				final int projectID = taskDAO.getProjectIDByName(projectName);
				final Project project = new Project();
				project.setProjectID(projectID);
				task.setProjectID(project);
			}

			task.setTaskName(request.getParameter("taskName"));

			final String assignedToUsername = request.getParameter("assignedTo");
			if (assignedToUsername != null && !assignedToUsername.isEmpty()) {
				final int assignedToID = TasksDAO.getUserIDByName(assignedToUsername);
				if (assignedToID != -1) { // Check if user ID is valid
					final User user = new User();
					user.setId(assignedToID);
					task.setId(user);

				}
			}

			task.setTaskStatus(request.getParameter("taskStatus"));

			final String createTimeParam = request.getParameter("createTime");
			final String taskDetails=request.getParameter("taskDetails");
			task.setTaskDetails(taskDetails);
			if (createTimeParam != null) {
				try {
					final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					final Date createTime = dateFormat.parse(createTimeParam);
					task.setCreateTime(createTime);
				} catch (final ParseException e) {
					// Handle parsing exception
					e.printStackTrace();
				}
			}

			final HttpSession session = request.getSession();

			final String currentUser = (String) session.getAttribute("currentUser");

			// final String createdByUsername = request.getParameter("createdBy");
			if (currentUser != null) {
				final int createdByID = TasksDAO.getUserIDByName(currentUser);
				if (createdByID != -1) {
					final User createdByUser = new User();
					createdByUser.setId(createdByID);
					task.setCreatedByUser(createdByUser);
				}
			}

			// final String modifiedByUsername = request.getParameter("modifiedBy");
			if (currentUser != null) {
				final int modifiedByID = TasksDAO.getUserIDByName(currentUser);
				if (modifiedByID != -1) {
					final User modifiedByUser = new User();
					modifiedByUser.setId(modifiedByID);
					task.setModifiedByUser(modifiedByUser);
				}
			}

			taskDAO = new TasksDAO();
			taskDAO.addTasks(task);
			saveTaskHistory(task, "Added");

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}

	public String updateTask() {
		try {
			task = new Tasks();
			taskDAO = new TasksDAO();
			final String taskIDParam = request.getParameter("taskID");
			if (taskIDParam != null) {
				task.setTaskID(Integer.parseInt(taskIDParam));
			}

			final String projectName = request.getParameter("projectName");
			if (projectName != null) {
				final int projectID = taskDAO.getProjectIDByName(projectName);
				if (projectID != -1) { // Check if project exists
					final Project project = new Project();
					project.setProjectID(projectID);
					task.setProjectID(project);
				} else {
					// Handle case where project does not exist
					return "error";
				}
			}
			task.setTaskName(request.getParameter("taskName"));

			final String assignedToUsername = request.getParameter("assignedTo");
			if (assignedToUsername != null && !assignedToUsername.isEmpty()) {
				final int assignedToID = TasksDAO.getUserIDByName(assignedToUsername);
				if (assignedToID != -1) { // Check if user ID is valid
					final User user = new User();
					user.setId(assignedToID);
					task.setId(user);
				} else {
					// Handle case where user does not exist
					return "error";
				}
			}

			task.setTaskStatus(request.getParameter("taskStatus"));

			final String createTimeParam = request.getParameter("createTime");
			if (createTimeParam != null) {
				try {
					final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					final Date createTime = dateFormat.parse(createTimeParam);
					task.setCreateTime(createTime);
				} catch (final ParseException e) {
					// Handle parsing exception
					e.printStackTrace();
					return "error";
				}
			}

			final HttpSession session = request.getSession();

			final String currentUser = (String) session.getAttribute("currentUser");

			final String createdByUsername = request.getParameter("createdBy");
			if (createdByUsername != null) {
				final int createdByID = taskDAO.getUserIDByName(createdByUsername);
				if (createdByID != -1) {
					final User createdByUser = new User();
					createdByUser.setId(createdByID);
					task.setCreatedByUser(createdByUser);
				} else {
					// Handle case where createdBy user does not exist
					return "error";
				}
			}

			// final String modifiedByUsername = request.getParameter("modifiedBy");
			if (currentUser != null) {
				final int modifiedByID = taskDAO.getUserIDByName(currentUser);
				if (modifiedByID != -1) {
					final User modifiedByUser = new User();
					modifiedByUser.setId(modifiedByID);
					task.setModifiedByUser(modifiedByUser);
				} else {
					// Handle case where createdBy user does not exist
					return "error";
				}
			}
			final String taskDetails=request.getParameter("taskDetails");
			task.setTaskDetails(taskDetails);

			taskDAO.updateTask(task);
			saveTaskHistory(task, "Updated");

		} catch (final Exception e) {
			System.out.println(e.getMessage());
			return "error";
		}
		return "success";
	}


	public String deleteTask() {
		try {
			final String id = request.getParameter("taskID");
			System.out.println("id to delete: " + id);

			// Convert id to int if necessary
			final Integer taskId = Integer.parseInt(id);

			// Instantiate CompanyDAO and delete the company
			final TasksDAO taskDao = new TasksDAO();
			taskDao.deleteTask(taskId);
			saveTaskHistory(taskDao.getTaskById(taskId), "Deleted");

		} catch (final Exception e) {

			System.out.println(e.getMessage());
			return ERROR; // Return error status if any exception occurs
		}
		return SUCCESS;
	}

	private void saveTaskHistory(Tasks task, String action) {
		final TaskHistory taskHistory = new TaskHistory();

		final HttpSession session = request.getSession();

		final String currentUser = (String) session.getAttribute("currentUser");
		final Integer currentUserID = (Integer) session.getAttribute("id");
		final User user = new User();
		user.setId(currentUserID);
		taskHistory.setTaskId(task);
		taskHistory.setPerformedByID(user);
		taskHistory.setPerformedBy(currentUser);
		taskHistory.setPerformedAct(action);
		taskHistory.setCreateTime(new Date());
		taskHistory.setActiveFlag(task.getActiveFlag());
		taskDAO = new TasksDAO();
	taskDAO.saveTaskHistory(taskHistory);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public TasksDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TasksDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public Tasks getTask() {
		return task;
	}

	public void setTask(Tasks task) {
		this.task = task;
	}

	public Integer getTaskId() {
		return taskID;
	}

	public void setTaskId(Integer taskId) {
		this.taskID = taskId;
	}

	public Long getProjectId() {
		return projectID;
	}

	public void setProjectId(Long projectId) {
		this.projectID = projectId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void setServletResponse(HttpServletResponse response) {
	    this.response = response;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
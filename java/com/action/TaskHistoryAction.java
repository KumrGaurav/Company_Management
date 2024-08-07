package com.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.action.ServletRequestAware;

import com.dao.TaskHistoryDAO;
import com.dao.TasksDAO;
import com.models.TaskHistory;
import com.models.Tasks;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TaskHistoryAction extends ActionSupport implements ServletRequestAware {
	private TaskHistoryDAO taskHistoryDAO;
	private TasksDAO tasksDAO;
	private List<TaskHistory> taskHistories;
	private Tasks taskID;
	private User performedByID;
	private String performedBy;
	private String performedAct;
	private String taskStatus;
	private Date createTime;
	private HttpServletRequest request;
	private String action;

	@Override
	public String execute() {
		taskHistoryDAO = new TaskHistoryDAO();
		tasksDAO = new TasksDAO();
		taskHistories = taskHistoryDAO.getAllTaskHistory();

		// Add task names to the taskHistories list
		for (final TaskHistory taskHistory : taskHistories) {
			final TaskHistoryDAO tasksHistoryDao = new TaskHistoryDAO();
			final String taskName = tasksHistoryDao.getTaskName(taskHistory.getTaskId().getTaskID());
			taskHistory.getTaskId().setTaskName(taskName);

			// final UserDAO userDAO = new UserDAO();
			final String performedBy = tasksHistoryDao.getUserName(taskHistory.getPerformedByID().getId());
			taskHistory.getPerformedByID().setFirstName(performedBy);
		}

		request.setAttribute("taskHistories", taskHistories);
		return SUCCESS;
	}

//	public String saveTaskHistory() {
//		final TaskHistory taskHistory = new TaskHistory();
//		taskHistory.setTaskId(taskID);
//		taskHistory.setTaskName(taskID.getTaskName());
//		taskHistory.setTaskStatus(taskID.getTaskStatus());
//		taskHistory.setPerformedByID(performedByID);
//		taskHistory.setPerformedBy(performedByID.getFirstName() + " " + performedByID.getLastName());
//		taskHistory.setPerformedAct(action);
//		taskHistory.setCreateTime(new Date(0));
//		taskHistory.setActiveFlag(taskID.getActiveFlag());
//
//		taskHistoryDAO.addTaskHistory(taskHistory);
//
//		return SUCCESS;
//	}

	@SuppressWarnings("unchecked")
	public String getTaskHistoriesByTaskId() {
		taskHistoryDAO.getTaskHistoriesByTaskId(taskID.getTaskID());
		// Add taskHistories to the value stack or request attributes
		return SUCCESS;
	}

	public TaskHistoryDAO getTaskHistoryDAO() {
		return taskHistoryDAO;
	}

	public void setTaskHistoryDAO(TaskHistoryDAO taskHistoryDAO) {
		this.taskHistoryDAO = taskHistoryDAO;
	}

	public TasksDAO getTasksDAO() {
		return tasksDAO;
	}

	public void setTasksDAO(TasksDAO tasksDAO) {
		this.tasksDAO = tasksDAO;
	}

	public List<TaskHistory> getTaskHistories() {
		return taskHistories;
	}

	public void setTaskHistories(List<TaskHistory> taskHistories) {
		this.taskHistories = taskHistories;
	}

	public Tasks getTaskID() {
		return taskID;
	}

	public void setTaskID(Tasks taskID) {
		this.taskID = taskID;
	}

	public User getPerformedByID() {
		return performedByID;
	}

	public void setPerformedByID(User performedByID) {
		this.performedByID = performedByID;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public String getPerformedAct() {
		return performedAct;
	}

	public void setPerformedAct(String performedAct) {
		this.performedAct = performedAct;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
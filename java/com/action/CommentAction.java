package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.dao.CommentDAO;
import com.models.Comment;
import com.models.Tasks;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("deprecation")
public class CommentAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<String> comments;
	private CommentDAO commentDao;
	private Comment comment;
	int commentID;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String execute() {
		return "success";
	}

	public String getAll() {
		final int taskID = Integer.parseInt(request.getParameter("taskID"));
		final CommentDAO commentDao = new CommentDAO();
		comments = commentDao.getCommentsForTaskID(taskID);

		final StringBuilder html = new StringBuilder();
		html.append("<ul>");
		for (final String comment : comments) {
			html.append("<li>").append(comment).append("</li>");
		}
		html.append("</ul>");

		request.setAttribute("commentsHtml", html.toString());
		return "success";
	}

	public String save() {
		try {
			final String formData = request.getParameter("formData");
			final int taskID = Integer.parseInt(request.getParameter("taskID"));
			final HttpSession session = request.getSession();
			final Integer userID = (Integer) session.getAttribute("id");
			// final int userID = Integer.parseInt(request.getParameter("assignedTo"));
			/*
			 * final String createTimeStr = request.getParameter("createTime"); final
			 * SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy, h:mm:ss a.SSS");
			 */
			/* final Date createTime = formatter.parse(createTimeStr); */

			final Comment comment = new Comment();
			comment.setComment(formData);
			comment.setCreateTime(new Date());
			comment.setStatus(1);

			final Tasks task = new Tasks();
			task.setTaskID(taskID); // set task ID
			comment.setTask(task); // set task object

			final User user = new User();
			user.setId(userID);
			comment.setUser(user); // set user object

			final CommentDAO commentDao = new CommentDAO();
			commentDao.addComment(comment);

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}

//	public String updateComment() {
//		try {
//			int projectID = Integer.parseInt(request.getParameter("projectID"));
//			String projectName = request.getParameter("projectName");
//			int userID = Integer.parseInt(request.getParameter("userID"));
//
//			System.out.println("Id of the project to update: " + projectID);
//
//			project = new Comment();
//			project.setStatus(1);
//			project.setProjectID(projectID);
//			project.setProjectName(projectName);
//			project.setUserID(userID);
//
//			projectDAO projectDao = new projectDAO();
//			projectDao.updateProject(project);
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return SUCCESS;
//	}

//
//	public String deleteProject() {
//		try {
//			int projectID = Integer.parseInt(request.getParameter("projectID"));
//			System.out.println("id to delete: " + projectID);
//
//			projectDAO projectDao = new CommentDAO();
//			projectDao.deleteProject(projectID);
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return SUCCESS;
//	}
//
//	public projectAction() {
//		super();
//	}

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

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public CommentDAO getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}
	/*
	 * try {JSONObject jsonObject = new JSONObject(); jsonObject.put("comments",
	 * comments); response.setContentType("application/json");
	 * response.getWriter().write(jsonObject.toString()); }catch(IOException e)
	 * {System.out.println(e);} return "success";
	 *
	 * JSONArray jsonArray = new JSONArray(comments); String jsonString =
	 * jsonArray.toString(); // Set the JSON string as a request attribute to be
	 * accessed in your JSP request.setAttribute("jsonString", jsonString);
	 */

}
package com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dao.AttachmentDAO;
import com.models.Attachment;
import com.models.Tasks;
import com.models.User;
import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("serial")
public class AttachmentAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	AttachmentDAO attachmentDAO = new AttachmentDAO();
	private Attachment attachment;
	private int attachmentId;
	private List<Attachment> attachments;
	private String uploadFileName;
	private File file;
	private HttpServletResponse response;
	private String userName;
	private Integer activeFlag;
	private Date attachmentDateTime;
	private User id;
	private String taskName;
	private Tasks taskId;
	private String attachmentName;
	private String attachmentPath;
	private String uploadContentType;

	private String destPath;
	private String filefile;

	public String execute() {

		attachments = attachmentDAO.listAttachment();

		// Add project names to the tasks list
		for (final Attachment attachment : attachments) {
			final AttachmentDAO attachmentDAO = new AttachmentDAO();
			final String taskName = attachmentDAO.getTaskName(attachment.getTaskId().getTaskID());
			attachment.setTaskName(taskName);

			final String userName = attachmentDAO.getUserName(attachment.getId().getId());
			attachment.setUserName(userName);

			final String attachmentBy = attachmentDAO.getUserName(attachment.getAttachmentByUser().getId());
			attachment.setAttachmentBy(attachmentBy);

		}

		return SUCCESS;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public String addAttachment(Attachment attachment) {
		AttachmentDAO attachmentDAO = new AttachmentDAO();

		attachmentDAO.addAttachment(attachment);
		return SUCCESS;
	}

	public String delete() {
		try {
			String id = request.getParameter("AttachmentId");
			System.out.println("id to delete: " + id);

			int attachmentId = Integer.getInteger(id);

			AttachmentDAO attachmentDAO = new AttachmentDAO();
			attachmentDAO.deleteAttachment(attachmentId);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return ERROR;

		}
		return SUCCESS;

	}

	public String save() {
		try {
			
			destPath = "C:/apache-tomcat-6.0.33/work/";
			File destFile = new File(destPath, filefile);
			FileUtils.copyFile(file, destFile);
			String taskName = request.getParameter("taskName");
			final int taskId = AttachmentDAO.getTaskIdByName(taskName);
			final Tasks task = new Tasks();
			task.setTaskID(taskId);
			attachment.setTaskId(task);

			attachment.setAttachmentName(request.getParameter("attachmentName"));

			final String attachmentBy = request.getParameter("attachmentBy");
			final int attachmentById = AttachmentDAO.getUserIDByName(attachmentBy);
			final User user = new User();
			user.setId(attachmentById);
			attachment.setId(user);

			final String attachmentDateTimeParam = request.getParameter("attachmentDateTime");
			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final Date attachmentDateTime = dateFormat.parse(attachmentDateTimeParam);
			attachment.setAttachmentDateTime(attachmentDateTime);

	}catch(Exception e)
	{
		e.printStackTrace();
	}
	attachmentDAO.addAttachment(attachment);
	return SUCCESS;
	}

	public String listAttachment() {
		attachments = attachmentDAO.listAttachment();
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	// Getters and Setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getAttachmentDateTime() {
		return attachmentDateTime;
	}

	public void setAttachmentDateTime(Date attachmentDateTime) {
		this.attachmentDateTime = attachmentDateTime;
	}

	public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Tasks getTaskId() {
		return taskId;
	}

	public void setTaskId(Tasks taskId) {
		this.taskId = taskId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	  public String getUploadFileName() { return uploadFileName; }
	  
	  public void setUploadFileName(String uploadFileName) { this.uploadFileName =
	  uploadFileName; }
	  
	  public String getUploadContentType() { return uploadContentType; }
	  
	  public void setUploadContentType(String uploadContentType) {
	  this.uploadContentType = uploadContentType; }
	 

	
	   public String getFileFile() {
	      return filefile;
	   }
	   
	   public void setFileFile(String filefile) {
	      this.filefile = filefile;
	   }
		/*
		 * public String save() { // edit this method try { // File file =
		 * (File)request.getParameter("file"); attachment = new Attachment();
		 * 
		 * byte[] fileParam = (request.getParameter("file")).getBytes(); // File file =
		 * new File(fileParam); attachment.setFile(fileParam);
		 * 
		 * // Retrieve the uploaded file File uploadedFile = attachment.getFile(); //
		 * Assuming 'getFile()' returns the uploaded file
		 * 
		 * // Get the file name String fileName = attachment.getAttachmentName(); //
		 * Assuming 'getAttachmentName()' returns the file name
		 * 
		 * // Define the upload directory String uploadPath =
		 * ServletActionContext.getServletContext().getRealPath("/uploads/");
		 * 
		 * // Create the upload directory if it doesn't exist File uploadDir = new
		 * File(uploadPath); if (!uploadDir.exists()) { uploadDir.mkdirs(); }
		 * 
		 * // Define the destination file File destFile = new File(uploadPath,
		 * fileName);
		 * 
		 * // Copy the uploaded file to the destination file //
		 * FileUtils.copyFile(uploadedFile, destFile);
		 * 
		 * // Now, you can save the attachment details to the database
		 * attachment.setAttachmentPath(destFile.getAbsolutePath()); // Assuming
		 * 'setAttachmentPath()' sets the file // path in the 'Attachment' object //
		 * attachmentDAO.addAttachment(attachment);
		 * 
		 * 
		 * final String attachmentIdParam = request.getParameter("attachmentId"); if
		 * (attachmentIdParam != null) {
		 * attachment.setAttachmentId(Integer.parseInt(attachmentIdParam)); } final
		 * String taskName = request.getParameter("taskName"); if (taskName != null) {
		 * final int taskId = AttachmentDAO.getTaskIdByName(taskName); final Tasks task
		 * = new Tasks(); task.setTaskID(taskId); attachment.setTaskId(task); }
		 * 
		 * attachment.setAttachmentName(request.getParameter("attachmentName"));
		 * 
		 * // code to edit and remove the errors final String attachmentBy =
		 * request.getParameter("attachmentBy"); if (attachmentBy != null &&
		 * !attachmentBy.isEmpty()) { final int attachmentById =
		 * AttachmentDAO.getUserIDByName(attachmentBy); if (attachmentById != -1) { //
		 * Check if user ID is valid final User user = new User();
		 * user.setId(attachmentById); attachment.setId(user);
		 * 
		 * } }
		 * 
		 * final String attachmentDateTimeParam =
		 * request.getParameter("attachmentDateTime"); if (attachmentDateTimeParam !=
		 * null) { try { final SimpleDateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); final Date attachmentDateTime =
		 * dateFormat.parse(attachmentDateTimeParam);
		 * attachment.setAttachmentDateTime(attachmentDateTime); } catch (final
		 * ParseException e) { // Handle parsing exception e.printStackTrace(); } }
		 * 
		 * attachmentDAO = new AttachmentDAO(); attachmentDAO.addAttachment(attachment);
		 * return SUCCESS;
		 * 
		 * } catch (Exception e) { System.out.println(e.getMessage()); return ERROR; } }
		 */
	   
	   
	   
	   /*
		 * try { attachment = new Attachment(); attachmentDAO = new AttachmentDAO();
		 * byte[] fileParam = (request.getParameter("file")).getBytes(); String
		 * uploadPath =
		 * ServletActionContext.getServletContext().getRealPath("/")+"fileParam";
		 * attachment.setAttachmentPath(uploadPath);
		 * 
		 * 
		 * String taskName = request.getParameter("taskName"); final int taskId =
		 * AttachmentDAO.getTaskIdByName(taskName); final Tasks task = new Tasks();
		 * task.setTaskID(taskId); attachment.setTaskId(task);
		 * 
		 * attachment.setAttachmentName(request.getParameter("attachmentName"));
		 * 
		 * final String attachmentBy = request.getParameter("attachmentBy"); final int
		 * attachmentById = AttachmentDAO.getUserIDByName(attachmentBy); final User user
		 * = new User(); user.setId(attachmentById); attachment.setId(user);
		 * 
		 * final String attachmentDateTimeParam =
		 * request.getParameter("attachmentDateTime"); final SimpleDateFormat dateFormat
		 * = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); final Date attachmentDateTime
		 * = dateFormat.parse(attachmentDateTimeParam);
		 * attachment.setAttachmentDateTime(attachmentDateTime); } catch (Exception e) {
		 * System.out.println(e.getMessage()); return ERROR; }
		 * 
		 * attachmentDAO.addAttachment(attachment);
		 * 
		 * return SUCCESS;
		 * 
		 */
}
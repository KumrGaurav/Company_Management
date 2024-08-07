package com.action;

import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.dao.EmployeeProjectDAO;
import com.models.EmployeeProject;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("deprecation")
public class EmployeeProjectAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<String> employees;
	private List<Integer> employeeID;

//successfully handled JSON data 
	public String getEmployeeList() {
	    int projectID = Integer.parseInt(request.getParameter("projectID"));

	    EmployeeProjectDAO employeeProjectDAO = new EmployeeProjectDAO();
	    List<EmployeeProject> employeeProjects = employeeProjectDAO.getUserID(projectID);
	    
	    employees = employeeProjectDAO.getUserFirstNames(employeeProjects);
	    employeeID = employeeProjectDAO.getUserIDs(employeeProjects);

	    JSONArray jsonArray = new JSONArray();
	    for (int i = 0; i < employees.size(); i++) {
	        JSONObject jsonObject = new JSONObject();
	        try {
	            jsonObject.put("id", employeeID.get(i));
	            jsonObject.put("name", employees.get(i));
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
	    return null; // Since we're directly writing the response, no need to return a JSP
	}


	

	@JSON(name = "employees")
	public List<String> getEmployees() {
		return employees;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String execute() {
		return "success";
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}

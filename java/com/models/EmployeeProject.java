package com.models;
public class EmployeeProject {
private int id ;
private User user;
private Project project;

public EmployeeProject() {
	super();
	// TODO Auto-generated constructor stub
}
public EmployeeProject(int id, User user, Project project) {
	super();
	this.id = id;
	this.user = user;
	this.project = project;
}
@Override
public String toString() {
	return "EmployeeProject [id=" + id + ", user=" + user + ", project=" + project + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Project getProject() {
	return project;
}
public void setProject(Project project) {
	this.project = project;
}


} 

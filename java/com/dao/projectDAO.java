
package com.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.models.Attachment;
import com.models.Project;
import com.models.Tasks;
import com.models.User;

public class projectDAO {
	private List<Project> projects; 
	private static SessionFactory sessionFactory;

	public projectDAO() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	
	public List<Project> getAllProjects() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query<Project> query1 = session.createQuery("from Project where status = 1",Project.class);
			projects = query1.getResultList();
			for (Project project : projects) {
				
				User user = session.get(User.class, project.getId().getId());
				if (user != null) {
					project.getId().setFirstName(user.getFirstName());
				} else {
					project.getId().setFirstName(null); // or some default value
				}


			}
			System.out.println(projects.size());
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return projects;

	}
	
	public List<Project> getAllProjectUsers(int projectID) {
		final Session session = sessionFactory.openSession();
		final Query<Project> query = session.createQuery("from Project where projectID: status = 1", Project.class);
		final List<Project> projects = query.getResultList();
		session.close();
		return projects;
	}

	public void addProject(Project project) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			project.setStatus(1); // set status to 1
			session.save(project);
			transaction.commit();
		} catch (Exception e) {
			/*
			 * if (transaction != null) { transaction.rollback(); }
			 */
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteProject(int projectID) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		final Project project = session.get(Project.class, projectID);
		try {
			transaction = session.beginTransaction();
			if (project != null) {
				project.setStatus(0);
				session.update(project);
			}
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	
	  public void updateProject(Project newProject) 
	  { 
		  final Session session = sessionFactory.openSession(); 
		  Transaction transaction = null;
		  try { transaction = session.beginTransaction(); 
		  final Project existingProject= session.get(Project.class, newProject.getProjectID());
		 // existingProject.setProjectID(newProject.getProjectID()); 
		  existingProject.setProjectName(newProject.getProjectName());
		  //existingProject.setId(newProject.getId());
		  existingProject.setUserName(newProject.getUserName());

	  
		  session.update(existingProject); transaction.commit(); } 
		  catch (Exception e) 
		  { 
			  if (transaction != null) 
			  { 
				  transaction.rollback(); 
			  }
			  e.printStackTrace(); 
		  } 
		  finally 
		  { 
			  session.close(); 
		  } 
	  }
	 
	public static int getUserIDByName(String userName) {
		final Session session = sessionFactory.openSession();
		final Query<User> query = session.createQuery("from User where firstName = :name", User.class);
		query.setParameter("name", userName);
		final User user = query.uniqueResult();
		session.close();
		return (user != null) ? user.getId() : -1; // Return -1 if user not found
	}

}

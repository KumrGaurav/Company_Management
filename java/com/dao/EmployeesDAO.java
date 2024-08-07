
package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.models.Company;
import com.models.Project;
import com.models.Tasks;
import com.models.User;

public class EmployeesDAO {
	private static SessionFactory sessionFactory;
	// private List<Employees> employees;
	private List<Tasks> employeeData;
	private List<Project> employeeProjects;

	public EmployeesDAO() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	}

	public static int getProjectIDByName(String projectName) {
		final Session session = sessionFactory.openSession();
		final Query<Project> query = session.createQuery("from Project where projectName = :name", Project.class);
		query.setParameter("name", projectName);
		final Project project = query.uniqueResult();
		session.close();
		return (project != null) ? project.getProjectID() : -1; // Return -1 if project not found
	}

//	public String getProjectName(Integer projectID) {
//		final Session session = sessionFactory.openSession();
//		final Project project = session.get(Project.class, projectID);
//		session.close();
//		return project.getProjectName();
//	}

	public String getProjectNameFromTask(int taskID) {
		final Session session = sessionFactory.openSession();
		final Tasks task = session.get(Tasks.class, taskID); // Assuming Task class exists and maps to your task table
		session.close();

		if (task != null) {
			final Project project = task.getProjectID(); // Assuming there's a method to get the project associated with
															// the task
			if (project != null) {
				return project.getProjectName();
			}
		}
		return null; // Or handle accordingly if project or task is not found
	}

//	public String getProjectNameFromTask(Integer taskID) {
//		final Session session = sessionFactory.openSession();
//		final Query<Tasks> query = session.createQuery("from Task where taskID = :id", Tasks.class);
//		query.setParameter("id", taskID);
//		final Tasks task = query.uniqueResult();
//		session.close();
//		return (task != null && task.getProjectID() != null) ? task.getProjectID().getProjectName() : null;
//	}

//	public String getEmployeeName(int companyID) {
//		final Session session = sessionFactory.openSession();
//		final Company company = session.get(Company.class, companyID); // Assuming Task class exists and maps to your
//																		// task table
//		session.close();
//
//		if (company != null) {
//			final User user = company.getUserID(); // Assuming there's a method to get the project associated with
//													// the task
//			if (user != null) {
//				return user.getFirstName();
//			}
//		}
//		return null; // Or handle accordingly if project or task is not found
//	}

	public static int getUserIDByName(String employeeName) {
		final Session session = sessionFactory.openSession();
		final Query<User> query = session.createQuery("from User where firstName = :name", User.class);
		query.setParameter("name", employeeName);
		final User user = query.uniqueResult();
		session.close();
		return (user != null) ? user.getId() : -1; // Return -1 if user not found
	}

	public String getEmployeeName(Integer userID) {
		final Session session = sessionFactory.openSession();
		final User user = session.get(User.class, userID);
		session.close();
		if (user.getRole().equalsIgnoreCase("Employee")) {
			return user.getFirstName();
		} else {
			return null;
		}

	}

//	public String getCompanyName(Long userID) {
//	    Session session = null;
//	    String companyName = null;
//	    try {
//	        session = sessionFactory.openSession();
//	        Query<Company> query = session.createQuery("FROM Company WHERE userID.id = :userID", Company.class);
//	        query.setParameter("userID", userID);
//	        Company company = query.uniqueResult();
//	        if (company != null) {
//	            companyName = company.getName();
//	        }
//	    } catch (HibernateException e) {
//	        // handle exceptions here
//	    } finally {
//	        if (session != null) {
//	            session.close();
//	        }
//	    }
//	    return companyName;
//	}

	public Integer getCompanyIDByName(String companyName) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Integer companyID = null;
		try {
			transaction = session.beginTransaction();
			final Query<Company> query = session.createQuery("FROM Company WHERE company_name = :name", Company.class);
			query.setParameter("name", companyName);
			final Company company = query.uniqueResult();
			if (company != null) {
				companyID = company.getCompanyId();
			}
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return companyID;
	}

	public String getCompanyName(Long companyID) {
		final Session session = sessionFactory.openSession();
		final Company company = session.get(Company.class, companyID);
		session.close();
		return company.getCompanyName();
	}

	public static int getTaskIDByName(String taskName) {
		final Session session = sessionFactory.openSession();
		final Query<Tasks> query = session.createQuery("from Tasks where taskName = :name", Tasks.class);
		query.setParameter("name", taskName);
		final Tasks task = query.uniqueResult();
		session.close();
		return (task != null) ? task.getTaskID() : -1; // Return -1 if task not found
	}

	public String getTaskName(Integer taskID) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.openSession();
		final Tasks task = session.get(Tasks.class, taskID);
		session.close();
		return task.getTaskName();
	}

	public List<Tasks> getLoggedInEmployeeTask(Integer userID) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		// List<Tasks> employee = null;
		try {
			transaction = session.beginTransaction();
			final Query<Tasks> query = session
					.createQuery("from Tasks where activeFlag=1 and id.id = :id and id.role='Employee'", Tasks.class);
			query.setParameter("id", userID);
			employeeData = query.getResultList();
			System.out.println(employeeData.size());
			System.out.println(employeeData);
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeData;
	}

	public List<Project> listEmployeeProjects(int employeeId) {
		// TODO Auto-generated method stub
		try
		{
			Session session = this.sessionFactory.openSession();
			List<Project> projects = null;
			final Query<Project> query = session.createQuery("FROM Project as P WHERE P.id.id = :employeeId AND P.status=1", Project.class);
			query.setParameter("employeeId", employeeId);
			projects = query.getResultList();

			System.out.println(projects.size());

			return projects;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Tasks> listEmployeeTasks(int employeeId, int projectId) {
		// TODO Auto-generated method stub
		try
		{
			Session session = this.sessionFactory.openSession();
			List<Tasks> tasks = null;
			final Query<Tasks> query = session.createQuery("FROM Tasks as T WHERE T.id.id = :employeeId AND T.projectID.projectID=: projectId AND T.activeFlag=1", Tasks.class);
			query.setParameter("employeeId", employeeId);
			query.setParameter("projectId", projectId);

			tasks = query.getResultList();

			System.out.println(tasks.size());

			return tasks;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void importFile(User entity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			entity.setActiveFlag(1);
			session.save(entity);

			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}

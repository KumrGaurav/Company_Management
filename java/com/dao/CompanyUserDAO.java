package com.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.action.CompanyUserAction;
import com.models.Company;
import com.models.Project;
import com.models.User;

public class CompanyUserDAO {
	private static SessionFactory sessionFactory;
	private String userName;
	private String password;
	List<User> employees = null;
	private int userId;

	// private int companyId=2;
	public CompanyUserDAO() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public List<Project> listCompanyProjects(int companyId) {
		// TODO Auto-generated method stub
		try
		{
			Session session = this.sessionFactory.openSession();
			List<Project> projects = null;
			final Query<Project> query = session.createQuery("FROM Project as P WHERE P.companyId.companyId = :companyId AND P.status=1", Project.class);
			query.setParameter("companyId", companyId);
			projects = query.getResultList();

			System.out.println(projects.size());

			return projects;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> listCompanyEmployees(int companyId) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			CompanyUserAction companyUserAction = new CompanyUserAction();
			String role = "employee";

			Query<User> query = session.createQuery("FROM User as model WHERE role =: role  AND model.companyId.companyId = :companyId AND model.activeFlag = 1",User.class);
			query.setParameter("companyId", companyId);
			query.setParameter("role", role);
			List<User> employees = query.getResultList();

			System.out.println(employees.size());
			return employees;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return employees;

	}

	public void userDetails(String userName, String password) {
		this.userName = userName;
		this.password = password;
		userId = getUserIDByName(userName);
	}

	public static int getUserIDByName(String userName) {
		final Session session = sessionFactory.openSession();
		final Query<User> query = session.createQuery("from User where firstName = :userName", User.class);
		query.setParameter("userName", userName);
		final User user = query.uniqueResult();
		session.close();
		return (user != null) ? user.getId() : -1; // Return -1 if user not found
	}

	public void addEmployee(User user) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user.setActiveFlag(1);
			session.save(user);

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

	public void withServletRequest(HttpServletRequest request1) {
		// TODO Auto-generated method stub

	}

	public void deleteEmployee(int employeeId, int modifiedByID) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = employeeId;

		Query<User> query = session.createQuery("FROM User as model WHERE id= :id", User.class);
		query.setParameter("id", id);
		final User user = query.uniqueResult();

		try {
			transaction = session.beginTransaction();
			if (user != null) {
				user.setActiveFlag(0);
				user.setModifiedBy(modifiedByID);
				user.setModifiedAt(new Date());
				session.update(user);
			}
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateEmployee(int employeeId, String companyUserFirstName, String companyUserLastName,
			String companyUserPassword, String companyUserEmail,int modifiedByID) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			String role = "employee";
			Query query1 = session.createQuery("from User as model where id = :employeeId");
			query1.setParameter("employeeId", employeeId);
			User user = (User) query1.uniqueResult();

			user.setFirstName(companyUserFirstName);
			user.setLastName(companyUserLastName);
			user.setPassword(companyUserPassword);
			user.setEmail(companyUserEmail);
			user.setRole("employee");
			user.setModifiedBy(modifiedByID);
			user.setModifiedAt(new Date());

			session.update(user);
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

	public List<User> listAllEmployees() {
		final Session session = this.sessionFactory.openSession();
		List<User> allEmployees = null;
		String role="employee";
		final Query<User> query = session.createQuery("from User where activeFlag=1 AND role=:role", User.class);
		query.setParameter("role", role);
		allEmployees = query.getResultList();

		System.out.println(allEmployees.size());

		return allEmployees;
	}
}
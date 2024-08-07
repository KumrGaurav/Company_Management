package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.models.Company;
import com.models.EmployeeProject;
import com.models.User;

public class EmployeeProjectDAO {
	private static SessionFactory sessionFactory;

	public EmployeeProjectDAO() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public List<EmployeeProject> getUserID(int projectID) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<EmployeeProject> employeeProject = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM EmployeeProject ep WHERE ep.project.projectID = :projectID";
			Query<EmployeeProject> query = session.createQuery(hql, EmployeeProject.class);
			query.setParameter("projectID", projectID);
			employeeProject = query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeProject;
	}

	public List<String> getUserFirstNames(List<EmployeeProject> employeeProject) {
		List<String> userFirstNames = new ArrayList<>();
		for (EmployeeProject ep : employeeProject) {
			User user = ep.getUser();
			String firstName = user.getFirstName();
			userFirstNames.add(firstName);
		}
		return userFirstNames;
	}

	public List<Integer> getUserIDs(List<EmployeeProject> employeeProjects) {
		List<Integer> userIDs = new ArrayList<>();
		for (EmployeeProject ep : employeeProjects) {
			User user = ep.getUser();
			Integer userID = user.getId(); // Assuming that User class has a getID() method
			userIDs.add(userID);
		}

		return userIDs;
	}
	public void addEmployeeProject(EmployeeProject employeeProject) {
        final Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(employeeProject);
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

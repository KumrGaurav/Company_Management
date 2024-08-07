
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
import com.models.User;

public class UserDao {
	private final SessionFactory sessionFactory;

	public UserDao() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public List<User> getAllUsers() {
		final Session session = sessionFactory.openSession();
		final Query<User> query = session.createQuery("from User where activeFlag=1", User.class);
		final List<User> users = query.getResultList();
		session.close();
		return users;
	}

	public void addUser(User user) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
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

	public void deleteUser(int id) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		final User user = session.get(User.class, id);
		try {
			transaction = session.beginTransaction();
			if (user != null) {
				session.delete(user);
			}
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public User getUserDetails(String userName, String password) {
		try {
			final Session session = sessionFactory.openSession();
		    session.beginTransaction();
		    Query<User> query = session.createQuery("from User where firstName = :userName and password = :password", User.class);
		    query.setParameter("userName", userName);
		    query.setParameter("password", password);
		    User user = query.uniqueResult();
		    //System.out.println(user.getCompanyId());
		    session.getTransaction().commit();
		    return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public User findIdByName(String userName) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		User user = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM User u WHERE u.userName = :userName";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("userName", userName);
			user = query.uniqueResult();
		transaction.commit();
		}catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	

}

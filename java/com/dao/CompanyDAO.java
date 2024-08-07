
package com.dao;

import java.util.Date;
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

public class CompanyDAO {
	private static SessionFactory sessionFactory;

	public CompanyDAO() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
				.build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public void addCompany(Company company) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			company.setActiveFlag(1);
			session.save(company);

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

	public String getCompanyNameById(int companyId) {
		final Session session = sessionFactory.openSession();
		try {
			// Retrieve the company name from the database by its ID
			final Company company = session.get(Company.class, companyId);
			if (company != null) {
				return company.getCompanyName();
			} else {
				return null; // or throw an exception if company is not found
			}
		} finally {
			session.close();
		}
	}

	public void updateCompany(int companyId, String newName, int modifiedBy, String companyUserFirstName,
			String companyUserLastName, String companyUserPassword, String companyUserEmail, User user1) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Company where companyId = :companyId");
			query.setParameter("companyId", companyId);
			Company company = (Company) query.uniqueResult();
			
			String role = "Company User";
			Query query1 = session.createQuery("from User as model where model.companyId.companyId = :companyId AND role= :role");
			query1.setParameter("companyId", companyId);
			query1.setParameter("role", role);
			User user = (User) query1.uniqueResult();

			company.setCompanyName(newName);
			company.setId(user1);
			company.setModifiedAt(new Date());

			int companyUserId = user.getId();
			user.setFirstName(companyUserFirstName);
			user.setLastName(companyUserLastName);
			user.setPassword(companyUserPassword);
			user.setEmail(companyUserEmail);

			session.update(user);
			session.update(company);
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

	public void deleteCompany(int companyId, User user1) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;

		final Company company = session.get(Company.class, companyId);

		try {
			transaction = session.beginTransaction();
			if (company != null) {
				company.setId(user1);
				company.setModifiedAt(new Date());
				company.setActiveFlag(0);
				session.update(company);
			}
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Company> listCompanies() {
		final Session session = this.sessionFactory.openSession();
		List<Company> companies = null;
		final Query<Company> query = session.createQuery("from Company where activeFlag=1", Company.class);
		companies = query.getResultList();

		System.out.println(companies.size());
		System.out.println(companies);
		return companies;
	}

	public Company findIdByName(String companyName) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Company company = null;
		try {
			transaction = session.beginTransaction();
			String hql = "FROM Company c WHERE c.name = :companyName";
			Query<Company> query = session.createQuery(hql, Company.class);
			query.setParameter("companyName", companyName);
			company = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return company;
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

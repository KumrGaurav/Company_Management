
package com.dao;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.models.Attachment;
import com.models.Tasks;
import com.models.User;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.List;

public class AttachmentDAO {
	private static SessionFactory sessionFactory;
	private List<Attachment> attachments;

	public AttachmentDAO() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	}

	public List<Attachment> listAttachment() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Attachment where activeFlag=1");
			Query<Attachment> query1 = query;
			attachments = query1.getResultList();
			for (final Attachment attachment : attachments) {
				final Tasks task = session.get(Tasks.class, attachment.getTaskId().getTaskID());
				if (task != null) {
					task.setTaskName(task.getTaskName());
				} else {
					task.setTaskName(null);
				}

				//attachment.getTaskId().setTaskName(task.getTaskName());


				final User user = session.get(User.class, attachment.getId().getId());
				if (user != null) {
					user.setFirstName(user.getFirstName());
				} else {
					user.setFirstName(null); // or some default value
				}

			}
			System.out.println(attachments.size());
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachments;

	}

	/*
	 * public void addAttachment(Attachment attachments) { Session session =
	 * sessionFactory.openSession(); Transaction transaction = null; try {
	 * transaction = session.beginTransaction(); attachments.setActiveFlag(1);
	 * session.save(attachments); transaction.commit(); } catch (Exception e) { if
	 * (transaction != null) { transaction.rollback(); } e.printStackTrace(); }
	 * finally { session.close(); }
	 * 
	 * }
	 */
	public void addAttachment(Attachment attachment) {
		
		Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(attachment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            // Handle the exception
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

	/*
	 * public String getAttachmentNameById(long attachmentId) { Session session =
	 * sessionFactory.openSession(); try { // Retrieve the company name from the
	 * database by its ID Attachment attachment = (Attachment)
	 * session.get(Attachment.class, attachmentId); if (attachment != null) { return
	 * attachment.getAttachmentName(); } else { return null; // or throw an
	 * exception if company is not found } } finally { session.close(); } }
	 */
	

	public String getTaskName(Integer taskId) {
		final Session session = sessionFactory.openSession();
		final Tasks task = session.get(Tasks.class, taskId);
		session.close();
		return task.getTaskName();
	}

	public String getUserName(Integer id) {
		final Session session = sessionFactory.openSession();
		final User user = session.get(User.class, id);
		session.close();
		return user.getFirstName();
	}
	public static int getTaskIdByName(String taskName) {
		final Session session = sessionFactory.openSession();
		final Query<Tasks> query = session.createQuery("from Tasks where taskName = :name", Tasks.class);
		query.setParameter("name", taskName);
		final Tasks task = query.uniqueResult();
		session.close();
		return (task != null) ? task.getTaskID() : -1; // Return -1 if project not found
	}

	public static int getUserIDByName(String userName) {
		final Session session = sessionFactory.openSession();
		final Query<User> query = session.createQuery("from User where firstName = :name", User.class);
		query.setParameter("name", userName);
		final User user = query.uniqueResult();
		session.close();
		return (user != null) ? user.getId() : -1; // Return -1 if user not found
	}


	public List<Attachment> getAttachment() {
		return attachments;
	}

	public void setAttachment(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void deleteAttachment(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			Attachment attachment = (Attachment) session.get(Attachment.class, id);

			if (attachment != null) {
				attachment.setActiveFlag(0);
				session.update(attachment);
			}

			if (transaction.isActive()) {
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

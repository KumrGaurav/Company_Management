package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.models.Comment;
import com.models.Tasks;

public class CommentDAO {
	private SessionFactory sessionFactory;

	public CommentDAO() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public List<String> getCommentsForTaskID(Integer taskID) {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();

	    Query<String> query = session.createQuery("SELECT c.comment FROM Comment c WHERE c.task.taskID = :taskID", String.class);
	    query.setParameter("taskID", taskID);

	    List<String> comments = query.getResultList();
	    System.out.println("Comments are: "+comments);

	    session.getTransaction().commit();
	    return comments;
	}

	public void addComment(Comment comment) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			comment.setStatus(1); // set status to 1
			session.save(comment);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteComment(int commentID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Comment comment = (Comment) session.get(Comment.class, commentID);
		try {
			transaction = session.beginTransaction();
			if (comment != null) {
				comment.setStatus(0);
				session.update(comment);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Comment> getAllComment() {
		final Session session = sessionFactory.openSession();
		List<Comment> comments = null;
		final Query<Comment> query = session.createQuery("from Comment where status=1", Comment.class);
		comments = query.getResultList();
		
		System.out.println("Comments are: "+comments);
		
		System.out.println(comments.size());
		System.out.println(comments);
		session.close();
		
		return comments;
	}

	public void updateComment(Comment newComment) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Comment existingComment = (Comment) session.get(Comment.class, newComment.getCommentID());
			System.out.println("existingComment is : " + newComment.getStatus());
			existingComment.setComment(newComment.getComment());

			existingComment.setStatus(newComment.getStatus());

			// Set the createTime, task, and user objects in the existingComment object
			existingComment.setCreateTime(newComment.getCreateTime());
			existingComment.setTask(newComment.getTask());
			existingComment.setUser(newComment.getUser());

			session.update(existingComment);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
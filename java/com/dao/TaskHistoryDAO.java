package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.models.TaskHistory;
import com.models.Tasks;
import com.models.User;

public class TaskHistoryDAO {
	private static SessionFactory sessionFactory;

	public TaskHistoryDAO() {
		try {
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (final Exception e) {
			// Log the exception here
			e.printStackTrace();
		}
	}

	public List<TaskHistory> getAllTaskHistory() {
		final Session session = sessionFactory.openSession();
		try {
			final Query<TaskHistory> query = session.createQuery("from TaskHistory", TaskHistory.class);
			final List<TaskHistory> taskHistories = query.getResultList();

			for (final TaskHistory taskHistory : taskHistories) {
				final Tasks task = session.get(Tasks.class, taskHistory.getTaskId().getTaskID());
				taskHistory.getTaskId().setTaskName(task.getTaskName());

				final User user = session.get(User.class, taskHistory.getPerformedByID().getId());
				if (user != null) {
					taskHistory.getPerformedByID().setFirstName(user.getFirstName());
				} else {
					taskHistory.getPerformedByID().setFirstName(null); // or some default value
				}
			}
			System.out.println("Size of History table :" + taskHistories.size());
			System.out.println(taskHistories);

			return taskHistories;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaskHistory> getTaskHistoriesByTaskId(Integer taskId) {
		final Session session = sessionFactory.getCurrentSession();
		final Query<TaskHistory> query = session.createQuery("FROM TaskHistory WHERE taskId.taskID = :taskId");
		query.setParameter("taskID", taskId);
		return query.getResultList();
	}

//	public void addTaskHistory(TaskHistory taskHistory) {
//		final Session session = sessionFactory.openSession();
//		Transaction transaction = null;
//		try {
//			transaction = session.beginTransaction();
//			session.save(taskHistory);
//			transaction.commit();
//		} catch (final Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}

	public String getTaskName(Integer taskID) {
		final Session session = sessionFactory.openSession();
		final Tasks task = session.get(Tasks.class, taskID);
		session.close();
		return task.getTaskName();
	}

	public String getUserName(Integer id) {
		final Session session = sessionFactory.openSession();
		final User user = session.get(User.class, id);
		session.close();
		return user.getFirstName();
	}

}

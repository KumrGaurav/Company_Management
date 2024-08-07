package com.dao;

import java.util.List;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.models.Project;
import com.models.TaskHistory;
import com.models.Tasks;
import com.models.User;

public class TasksDAO {
	TaskHistory taskHistory;
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	private static SessionFactory sessionFactory;

	public TasksDAO() {
		try {
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (final Exception e) {
			// Log the exception here
			e.printStackTrace();
		}
	}

	public List<Tasks> getAllTasks() {
		final Session session = sessionFactory.openSession();
		List<Tasks> tasks = null;
		final Query<Tasks> query = session.createQuery("from Tasks where activeFlag=1", Tasks.class);
		tasks = query.getResultList();

		for (final Tasks task : tasks) {
			final Project project = session.get(Project.class, task.getProjectID().getProjectID());
			task.getProjectID().setProjectName(project.getProjectName());

//			final User user = session.get(User.class, task.getId().getId());
//			task.getId().setFirstName(user.getFirstName());

			final User user = session.get(User.class, task.getId().getId());
			if (user != null) {
				task.getId().setFirstName(user.getFirstName());
			} else {
				task.getId().setFirstName(null); // or some default value
			}

		}

		System.out.println(tasks.size());

		session.close();
		return tasks;
	}

	public List<Tasks> getProjectTask(int projectID) {
		final Session session = sessionFactory.openSession();
		List<Tasks> tasks = null;
		try {
			String hql = "from Tasks where activeFlag = 1 and projectID.projectID = :projectID";
			Query<Tasks> query = session.createQuery(hql, Tasks.class);
			query.setParameter("projectID", projectID);
			tasks = query.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			;
		}

		return tasks;
	}

	public Tasks addTasks(Tasks task) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Tasks newTask = null;
		try {
			
			transaction = session.beginTransaction();
			task.setActiveFlag(1); // set status to 1
			final User assignedTo = session.get(User.class, task.getId().getId()); // get the User object for the assignedTo user
			task.setId(assignedTo);
			session.save(task);
			transaction.commit();
			newTask = task; // Assign the newly added task to return
			// saveTaskHistory(newTask, "Added");
		} catch (final Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return newTask;
	}
	public Tasks addTask(Tasks task) {
		
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Tasks newTask = null;

        try {
            transaction = session.beginTransaction();
            task.setActiveFlag(1); // set status to 1
            if (task.getId() != null && task.getId().getId() != 0) {
                User assignedTo = session.get(User.class, task.getId().getId());
                task.setId(assignedTo);
            } else {
                // Handle case where assignedTo is null
                System.out.println("Assigned to User is null");
                throw new RuntimeException("Assigned to User is null");
            }
            session.save(task);
            transaction.commit();
            newTask = task; // Assign the newly added task to return
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return newTask;
    }
	public Tasks addTask(Tasks task, String assignedToUsername) {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Tasks newTask = null;

		try {
			transaction = session.beginTransaction();
			task.setActiveFlag(1); // set status to 1
			int assignedToUserNo = Integer.parseInt(assignedToUsername);
			if (assignedToUserNo != 0) {
				String queryString = "from User as u where u.id = :assignedToUsername";
				Query queryObject = session.createQuery(queryString);
				queryObject.setParameter("assignedToUsername", assignedToUserNo);
				List<User> resultList = queryObject.list();
				User assignedTo = resultList.get(0);
				task.setId(assignedTo);
			} else {
				// Handle case where assignedTo is null
				System.out.println("Assigned to User is null");
				throw new RuntimeException("Assigned to User is null");
			}
			session.save(task);
			transaction.commit();
			newTask = task; // Assign the newly added task to return
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return newTask;
	}

	public void deleteTask(Integer taskID) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		final Tasks task = session.get(Tasks.class, taskID);
		try {
			transaction = session.beginTransaction();
			if (task != null) {
				task.setActiveFlag(0);
				session.update(task);
				// saveTaskHistory(task, "Deleted");

			}
			transaction.commit();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//	public void updateTask(Tasks newTask) {
//		final Session session = sessionFactory.openSession();
//		Transaction transaction = null;
//
//		try {
//			transaction = session.beginTransaction();
//			final Tasks existingTask = session.get(Tasks.class, newTask.getTaskID());
//
//			existingTask.setTaskID(newTask.getTaskID());
//			existingTask.setTaskName(newTask.getTaskName());
//			existingTask.setId(newTask.getId());
//			existingTask.setProjectID(newTask.getProjectID());
//			existingTask.setCreatedByUser(newTask.getCreatedByUser());
//			existingTask.setModifiedByUser(newTask.getModifiedByUser());
//			existingTask.setTaskStatus(newTask.getTaskStatus());
//			existingTask.setCreateTime(newTask.getCreateTime());
//
//			session.update(existingTask);
//			transaction.commit();
//			saveTaskHistory(existingTask, "Updated");
//
//		} catch (final Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}
	public void updateTask(Tasks newTask) {
	    final Session session = sessionFactory.openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();
	        System.out.println("Updating Task ID: " + newTask.getTaskID());

	        final Tasks existingTask = session.get(Tasks.class, newTask.getTaskID());
	        if (existingTask == null) {
	            System.out.println("No task found with ID: " + newTask.getTaskID());
	            return;
	        }

	        existingTask.setTaskID(newTask.getTaskID());
	        existingTask.setTaskName(newTask.getTaskName());
	        existingTask.setId(newTask.getId());
	        existingTask.setProjectID(newTask.getProjectID());
	        existingTask.setCreatedByUser(newTask.getCreatedByUser());
	        existingTask.setModifiedByUser(newTask.getModifiedByUser());
	        existingTask.setTaskStatus(newTask.getTaskStatus());
	        existingTask.setCreateTime(newTask.getCreateTime());

	        session.update(existingTask);
	        transaction.commit();
//	        saveTaskHistory(existingTask, "Updated");

	    } catch (final Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}


	public void saveTaskHistory(TaskHistory taskHistory) {
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(taskHistory);
			session.getTransaction().commit();
			session.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	public Tasks getTaskById(Integer taskId) {
		final Session session = sessionFactory.openSession();
		final Tasks task = session.get(Tasks.class, taskId);
		session.close();
		return task;
	}

	public String getProjectName(Integer projectID) {
		final Session session = sessionFactory.openSession();
		final Project project = session.get(Project.class, projectID);
		session.close();
		return project.getProjectName();
	}

	public String getUserName(Integer id) {
		final Session session = sessionFactory.openSession();
		final User user = session.get(User.class, id);
		session.close();
		return user.getFirstName();
	}

	public static int getProjectIDByName(String projectName) {
		final Session session = sessionFactory.openSession();
		final Query<Project> query = session.createQuery("from Project where projectName = :name", Project.class);
		query.setParameter("name", projectName);
		
		final Project project = query.uniqueResult();
		session.close();
		return (project != null) ? project.getProjectID() : -1; // Return -1 if project not found
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
package com.piccy.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.piccy.demo.domain.Post;
import com.piccy.demo.domain.User;

@Repository(value = "userDao")
@Transactional
public class UserDao {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	// private SessionFactory  sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	public void createUser(User user) {
		Transaction tx = null;
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}
		catch (Exception ex) {
			System.out.println(ex.getStackTrace().toString());
		}
		finally {
			session.close();
		}

	}
	
	public User getUserByName(String username) {
		Transaction tx = null;
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			tx = session.beginTransaction();
			
			CriteriaQuery<User> c = entityManagerFactory.getCriteriaBuilder().createQuery(User.class);
			Root<User> from = c.from(User.class);
			c.select(from);
			c.where(entityManagerFactory.getCriteriaBuilder().equal(from.get("username"),username));
			
			List<User> users = entityManager.createQuery(c).getResultList();
			if (users.size() == 0) {
				return null;
			}
			return users.get(0);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace().toString());
		} finally {
			session.close();
		}
		return null;
	}
}
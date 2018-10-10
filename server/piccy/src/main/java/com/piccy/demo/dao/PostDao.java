package com.piccy.demo.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


import com.piccy.demo.domain.Post;


@Repository(value = "postDao")
@Transactional
public class PostDao {
	

//	@Autowired
//	private SessionFactory sessionFactory;
////	
//	@Autowired
//	private EntityManager entiyManager;
//	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
//	public SessionFactory getSessionFactory() {
//	//	return sessionFactory;
//	}
	

//	public void setSessionFactory(SessionFactory sessionFactory) {
//
//		this.sessionFactory = sessionFactory;
//	}
	
	Transaction tx = null;

	//create and save post to db
	public void createPost(Post post) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			tx = session.beginTransaction();
			session.save(post);
			tx.commit();
		}
		catch (Exception ex) {
			System.out.println(ex.getStackTrace().toString());
		}
		finally {
			
		}

		//sessionFactory.getCurrentSession().save(post);
	}
		
}

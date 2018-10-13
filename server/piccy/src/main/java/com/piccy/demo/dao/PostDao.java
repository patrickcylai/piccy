package com.piccy.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


import com.piccy.demo.domain.Post;


@Repository(value = "postDao")
@Transactional
public class PostDao {
	

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	private SessionFactory  sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
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

	}
	
	public List getAllPost() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		return session.createCriteria(Post.class).list();
	}
	
	public List getPostsByUser(int userid) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaQuery<Post> c = entityManagerFactory.getCriteriaBuilder().createQuery(Post.class);
		Root<Post> from = c.from(Post.class);
		c.select(from);
		c.where(entityManagerFactory.getCriteriaBuilder().equal(from.get("userId"),1));
		
		return entityManager.createQuery(c).getResultList(); 
		
	}
	

	

	

 	
}

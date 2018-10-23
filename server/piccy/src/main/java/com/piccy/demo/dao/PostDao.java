package com.piccy.demo.dao;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
import com.piccy.demo.domain.Rating;
import com.piccy.demo.responses.DeleteResponse;
import com.piccy.demo.responses.RatingResponse;


@Repository(value = "postDao")
@Transactional
public class PostDao {
	

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	private SessionFactory  sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	

	public void createPost(Post post) {
		Transaction tx = null;
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
			session.close();
		}

	}
	
	public List getAllPost() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		List results = session.createCriteria(Post.class).list();
		session.close();
		return results;
	}
	
	
	public List getPostsByUser(int userid) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaQuery<Post> c = entityManagerFactory.getCriteriaBuilder().createQuery(Post.class);
		Root<Post> from = c.from(Post.class);
		c.select(from);
		c.where(entityManagerFactory.getCriteriaBuilder().equal(from.get("userId"),userid));
		
		return entityManager.createQuery(c).getResultList(); 
	}
	
	
	public Post getPost(int postid) {
		try {
			
		
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaQuery<Post> c = entityManagerFactory.getCriteriaBuilder().createQuery(Post.class);
		Root<Post> from = c.from(Post.class);
		c.select(from);
		c.where(entityManagerFactory.getCriteriaBuilder().equal(from.get("id"),postid));
		
		return entityManager.createQuery(c).getSingleResult(); 
		
		}
		catch (NoResultException ex ) {
			return null;
		}
	}
	
	
	public Rating getRating(Post post, int userid) {
		try {
			
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaQuery<Rating> c = entityManagerFactory.getCriteriaBuilder().createQuery(Rating.class);
		Root<Rating> from = c.from(Rating.class);
		c.select(from);
		CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
		c.where(
			cb.equal(from.get("post"),post),
			cb.equal(from.get("userId"), userid)
		);
		
		
		return entityManager.createQuery(c).getSingleResult(); 
		
		}
		catch (NoResultException ex ) {
			System.out.println(ex.getLocalizedMessage().toString());
			return null;
		}
		
	}
	
	
	public Rating createRating(Post post, Rating rating) {
		
		Transaction tx = null;
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			tx = session.beginTransaction();
			System.out.println(rating.getPost().toString());
			post.addRating(rating);
			session.update(rating);
			session.update(post);
			
		//	session.save(post);
			tx.commit();
			
			return rating;
		}
		catch (Exception ex) {
			System.out.println(ex.getStackTrace().toString());
			return null;
		}
		finally {
			session.close();
		}
	
	}
	
	
	public RatingResponse getAllRatings(Post post) {
		try {
			
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<Rating> from = cq.from(Rating.class);
			cq.select(cb.count(from));
			cq.where(
					cb.equal(from.get("post"),post),
					cb.equal(from.get("isLike"), true)
				);
			
			Long likes = entityManager.createQuery(cq).getSingleResult();
		
			cq.select(cb.count(from));
			cq.where(
					cb.equal(from.get("post"),post),
					cb.equal(from.get("isLike"), false)
				);
			
			Long dislikes = entityManager.createQuery(cq).getSingleResult();
			
			RatingResponse ratingResponse = new RatingResponse(post.getId());
			ratingResponse.setDislikes(dislikes);
			ratingResponse.setLikes(likes);
			
			return ratingResponse;
			
			
		
		}
		catch (NoResultException ex ) {
			System.out.println(ex.getLocalizedMessage().toString());
			return null;
		}
		
		
	}
	
	
	
	public DeleteResponse deletePost(int postid) {	

		Post post = entityManager.find(Post.class, postid);

		try {
				
			int userid = post.getUserId();
			entityManager.remove(post);
			flushAndClear();
			return new DeleteResponse(postid, userid, "Success");

		}
		catch (Exception ex){
			return new DeleteResponse(postid, -1, "post doesnt exist");
		}

	}
	
	private void flushAndClear() {
	    entityManager.flush();
	    entityManager.clear();
	}
	

	

	

 	
}

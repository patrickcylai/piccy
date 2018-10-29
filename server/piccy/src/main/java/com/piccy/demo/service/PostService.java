package com.piccy.demo.service;
 


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piccy.demo.domain.Rating;
import com.piccy.demo.responses.DeleteResponse;
import com.piccy.demo.responses.PostRatingResponse;
import com.piccy.demo.responses.RatingResponse;
import com.piccy.demo.domain.Post;
import com.piccy.demo.dao.PostDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
//import javax.persistence.EntityManagerFactory;


@Service(value="postService")
@ComponentScan("com.piccy.demo")

public class PostService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	

	
	@Autowired
	private PostDao postDao;
	
	public void createPost(Post post) {
		postDao.createPost(post);
	}
	
	public List getAllPosts() {
		List<Post> allPosts =  postDao.getAllPost();

		List<PostRatingResponse> results = new ArrayList<PostRatingResponse>();
		for (Post post : allPosts ) {
			int postid = post.getId();
			RatingResponse rating = getLikes(postid);	
			
			PostRatingResponse current_rating = new PostRatingResponse(post, rating);
			System.out.println(current_rating);
			results.add(current_rating);
		}
		
		return results;
	}
	
	
	public List getPostByUser(int userid) {
		
		List<Post> allPosts =  postDao.getPostsByUser(userid);

		List<PostRatingResponse> results = new ArrayList<PostRatingResponse>();
		for (Post post : allPosts ) {
			int postid = post.getId();
			RatingResponse rating = getLikes(postid);	
			
			PostRatingResponse current_rating = new PostRatingResponse(post, rating);
			System.out.println(current_rating);
			results.add(current_rating);
		}
		
		return results;
		
	}
	
	public DeleteResponse deletePost(int postid) {
		return postDao.deletePost(postid);
	}
	
	public PostRatingResponse getPostAndRatingByID(int postid) {
		Post post = postDao.getPost(postid);
		
		if (post == null) {
			System.out.println("no post for id of " + postid);
			
			return null;
		}

		RatingResponse rating = getLikes(postid);
		
		PostRatingResponse result = new PostRatingResponse(post, rating);

	
		return result;
	
		
	}
	
	
	
	public Post getPostByID(int postid) {
		Post post = postDao.getPost(postid);
		
		if (post == null) {
			System.out.println("no post for id of " + postid);
			
			return null;
		}
		else {
			return postDao.getPost(postid);
		}
		
	}
	
	
	
	/*
	 * submits a like, should check if the user has liked post before
	 * */
	public Rating ratePost(int postid, int userid, boolean isLike) {
		
		Rating rating;
	
		Post  post = this.getPostByID(postid);
		if (post == null ) {
			return null; //returns null if there is no post
		}
		
		//if the rating doesn't exit create one
		rating = postDao.getRating(post, userid);
		if (rating == null) {
			rating = new Rating(post);
			rating.setUserId(userid); //set the rating of the post
		}	
		
		rating.setLike(isLike);
	
		postDao.createRating(post, rating);
		
		return rating;
	}
	
	/*
	 * should return all the likes for a given post
	 */
	public RatingResponse getLikes(int postid) {
		
		Rating rating;
		
		Post  post = this.getPostByID(postid);
		if (post == null ) {
			return null; //returns null if there is no post
		}
		
		return postDao.getAllRatings(post);
	
		
	}
	
	

}

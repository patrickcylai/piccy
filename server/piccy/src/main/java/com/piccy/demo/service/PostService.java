package com.piccy.demo.service;
 


import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piccy.demo.domain.Like;
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
		return postDao.getAllPost();
	}
	
	
	public List getPostByUser(int userid) {
		return postDao.getPostsByUser(userid);
	}
	
	
	/*
	 * submits a like, should check if the user has liked post before
	 * */
	public void likePost(Like like) {
		
	}
	
	/*
	 * should return all the likes for a given post
	 */
	public void getLikes(Post post) {
		
	}
	
	

}

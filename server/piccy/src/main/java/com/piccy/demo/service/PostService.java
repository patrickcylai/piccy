package com.piccy.demo.service;
 


import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	
	public void getPost(int id) {
		
	}
	
	

}

package com.piccy.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.piccy.demo.service.DeleteResponse;
import com.piccy.demo.service.FileResponse;
import com.piccy.demo.service.FileStorageService;

import com.piccy.demo.service.PostService;
import com.piccy.demo.domain.Post;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@ComponentScan("com.piccy.demo.service")
public class PostController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileStorageService fileStorageService;
	private final AtomicLong counter = new AtomicLong();

		
	
	
	
	@RequestMapping(value = "/posts/create", method = RequestMethod.POST)
	public Post createPost(@RequestParam("userid") int userid, @RequestParam("file") MultipartFile file) {
		
		String filename = fileStorageService.storeFile(file);
		//TODO: generate filename for storage bucket
		String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
		
		Post post = new Post();
		post.setImageRef(filename);
		post.setUserId(userid);
		post.setCreationDate(new Date());
		
		postService.createPost(post);
		
		return post;
		
	}
	
	@RequestMapping(value = "/posts/all", method = RequestMethod.GET)
	public List getAllPosts()
	{
		return postService.getAllPosts();
		
	}
	
	
	@RequestMapping(value = "/posts/{userid:.+}", method = RequestMethod.GET)
	public List getPost(@PathVariable int userid, HttpServletRequest request) {
		return postService.getPostByUser(userid);
	}
	
	@RequestMapping(value="/posts/delete/{postid:.+}", method=RequestMethod.POST)
	public DeleteResponse deletePost(@PathVariable int postid, HttpServletRequest request) {
		//TODO: needs to also delete file
		return postService.deletePost(postid);
	}
	
	
	
	
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public void likePost(@RequestParam("postId") int postId, @RequestParam("userId") int userId ) {
		//TODO fill out body
	}
	
	
	
	

}

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.piccy.demo.service.FileStorageService;

import com.piccy.demo.service.PostService;
import com.piccy.demo.service.LoginService;
import com.piccy.demo.domain.Post;
import com.piccy.demo.domain.Rating;
import com.piccy.demo.responses.DeleteResponse;
import com.piccy.demo.responses.FileResponse;
import com.piccy.demo.responses.RatingResponse;

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
	private LoginService loginService;
	
	@Autowired
	private FileStorageService fileStorageService;
	private final AtomicLong counter = new AtomicLong();
	
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/post/create", method = RequestMethod.POST)
	public Post createPost(@RequestParam("userid") String userid, @RequestParam("file") MultipartFile file) {
		Post post = new Post();

		System.out.println(loginService.useridExists(Integer.parseInt(userid)));
		
		if (!loginService.useridExists(Integer.parseInt(userid))) {
			return post;
		}
		
		String filename = fileStorageService.storeFile(file);
		//TODO: generate filename for storage bucket
		String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
		
		post.setImageRef(filename);
		post.setUserId(Integer.parseInt(userid));
		post.setCreationDate(new Date());
		
		postService.createPost(post);
		
		return post;
		
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/posts/all", method = RequestMethod.GET)
	public List getAllPosts()
	{
		return postService.getAllPosts();
		
	}

	
	/*for getting by user id*/
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/posts/", method = RequestMethod.GET)
	public List getPost(@RequestParam("userid") int userid) {
		return postService.getPostByUser(userid);
	}
	
	/*for getting indivifual post*/
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/post/{postid:.+}", method = RequestMethod.GET)
	public Post getPostSingle(@PathVariable int postid) {	
		return postService.getPostByID(postid);
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/post/{postid:.+}/delete", method=RequestMethod.POST)
	public DeleteResponse deletePost(@PathVariable int postid, HttpServletRequest request) {
		//TODO: needs to also delete file
		return postService.deletePost(postid);
	}
	
	
	
	
	
	@RequestMapping(value = "post/{postid:.+}/rate", method = RequestMethod.POST)
	public Rating likePost(@PathVariable("postid") int postid, @RequestParam("userid") int userid, @RequestParam("isLike") boolean isLike ) {
		
		Rating rating = postService.ratePost(postid, userid, isLike);
		//if there is no post, return no post
		if (rating == null) {
			Rating nullRating = new Rating();
			nullRating.setUserId(-1);
			return nullRating;
	
		}
		return rating;
	}
	
	
	@RequestMapping(value = "post/{postid:.+}/allratings", method = RequestMethod.GET)
	public RatingResponse getRatins(@PathVariable("postid") int postid) {
		
		RatingResponse ratings = postService.getLikes(postid);
		//if there is no post, return no post
		if (ratings == null) {
			return new RatingResponse(-1);
	
		}
		return ratings;
		
	}
	
	
	
	
	

}

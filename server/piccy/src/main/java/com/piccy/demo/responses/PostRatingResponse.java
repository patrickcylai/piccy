package com.piccy.demo.responses;

import com.piccy.demo.domain.Post;
import com.piccy.demo.domain.Rating;
import com.piccy.demo.domain.User;

public class PostRatingResponse {
	
	private Post post;
	
	private RatingResponse rating;
	
	private User user;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public RatingResponse getRating() {
		return rating;
	}

	public void setRating(RatingResponse rating) {
		this.rating = rating;
	}
	
	public PostRatingResponse(Post post, RatingResponse rating, User user) {
		this.post = post;
		this.rating = rating;
		this.user = user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}

}

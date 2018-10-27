package com.piccy.demo.responses;

import com.piccy.demo.domain.Post;
import com.piccy.demo.domain.Rating;

public class PostRatingResponse {
	
	private Post post;
	
	private RatingResponse rating;

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
	
	public PostRatingResponse(Post post, RatingResponse rating) {
		this.post = post;
		this.rating = rating;
	}
	
	

}

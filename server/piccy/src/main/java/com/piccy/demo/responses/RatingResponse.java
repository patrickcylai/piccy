package com.piccy.demo.responses;

import java.util.Date;

public class RatingResponse {
	
	private int postid;
	
	private long likes;
	
	private long dislikes;

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getDislikes() {
		return dislikes;
	}

	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	
	
	public RatingResponse(int postid) {
		this.postid = postid;
	}
	


    

}

package com.piccy.demo.service;

public class DeleteResponse {
	
	
	private int postid;
	
	private int userid;
	
	private String status;
	
	
	
	public int getPostid() {
		return postid;
	}
	public int getUserid() {
		return userid;
	}

	public String getStatus() {
		return status;
	}


	public DeleteResponse(int postid, int userid, String status) {
		this.postid = postid;
		this.userid = userid;
		this.status = status;
	}
	
	

}

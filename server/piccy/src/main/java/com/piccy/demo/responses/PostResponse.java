package com.piccy.demo.responses;

import java.util.Date;

import javax.persistence.Column;

public class PostResponse {
	
	
    private int id;
    
    private int userId;
    
    private String imageRef;
    
    private Date creationDate;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getImageRef() {
		return imageRef;
	}



	public void setImageRef(String imageRef) {
		this.imageRef = imageRef;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public PostResponse(int id, int userId, String imageRef, Date creationDate) {
		this.id = id;
		this.userId = userId;
		this.imageRef = imageRef;
		this.creationDate = creationDate;
	}
	
	

	

}

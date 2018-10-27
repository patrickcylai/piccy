package com.piccy.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name="post")
public class Post {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "userId")
    private int userId;
    
    @Column(name = "imageRef")
    private String imageRef;
    
    @Column(name = "creationDate")
    private Date creationDate;
    
    
    public int getId() {return id; }
    public void setId(int id) { this.id = id;}
    
    public int getUserId() { return userId; } 
    public void setUserId(int userId) { this.userId = userId;}
    
    public String getImageRef() { return imageRef; } 
    public void setImageRef(String imageRef) { this.imageRef = imageRef;}
    
    public Date getCreationDate() {return this.creationDate;}
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    
    

}

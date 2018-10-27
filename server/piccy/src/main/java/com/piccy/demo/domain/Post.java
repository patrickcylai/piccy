package com.piccy.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="post")
public class Post {
	
    @Id
    @Column(name = "post_id")
    @GeneratedValue
    private int id;
    
    @Column(name = "userId")
    private int userId;
    
    @Column(name = "imageRef")
    private String imageRef;
    
    @Column(name = "creationDate")
    private Date creationDate;
    
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Rating> ratings;
    
    
    public Set<Rating> getRatings() {return ratings;}
    
	public void addRating(Rating rating) {
		this.ratings.add(rating);
		rating.setPost(this);
		
	}
	
	public int getId() {return id; }
    public void setId(int id) { this.id = id;}
    
    public int getUserId() { return userId; } 
    public void setUserId(int userId) { this.userId = userId;}
    
    public String getImageRef() { return imageRef; } 
    public void setImageRef(String imageRef) { this.imageRef = imageRef;}
    
    public Date getCreationDate() {return this.creationDate;}
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    
    

}

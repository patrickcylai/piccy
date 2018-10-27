package com.piccy.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name="user")
public class User {
	
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;
    
    public int getUserid() {return userid; }
    public void setUserid(int userid) { this.userid = userid;}
    
    public String getUsername() { return username; } 
    public void setUsername(String username) { this.username = username;}
    
    public String getPassword() { return password; } 
    public void setPassword(String password) { this.password = password;}   
    
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email;}    

}

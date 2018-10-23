package com.piccy.demo.service;
 
import com.piccy.demo.domain.User;
import com.piccy.demo.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
//import javax.persistence.EntityManagerFactory;


@Service(value="loginService")
@ComponentScan("com.piccy.demo")

public class LoginService {
	@Autowired
	private UserDao userDao;
	
	public void createUser(User user) {
		userDao.createUser(user);
	}
	
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}
	
}
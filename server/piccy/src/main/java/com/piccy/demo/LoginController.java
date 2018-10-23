package com.piccy.demo;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class LoginController {
	public class LoginResponseJson
	{
		private String userCookie;
		private Boolean success;
		
		public String getUserCookie() {
			return userCookie;
		}
		
		public void setUserCookie(final String newValue) {
			userCookie = newValue;
		}
		
		public Boolean getSuccess() {
			return success;
		}
		
		public void setSuccess(final Boolean newValue) {
			success = newValue;
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseJson login(@RequestParam("username") String username, @RequestParam("password") String password) {
		// TODO: check these against actual database credentials
		final LoginResponseJson response = new LoginResponseJson();
		if (username.equals("user") && password.equals("password")) {
			// TODO: return an actual value that can be used for a cookie
			response.setUserCookie(username);
			response.setSuccess(true);
		} else {
			response.setUserCookie("");
			response.setSuccess(false);
		}
		return response;
	}
	
	public class IsValidResponseJson
	{
		private String username;
		private Boolean isValid;
		
		public String getUsername() {
			return username;
		}
		
		public void setUsername(final String newValue) {
			username = newValue;
		}
		
		public Boolean getIsValid() {
			return isValid;
		}
		
		public void setIsValid(final Boolean newValue) {
			isValid = newValue;
		}
	}
	
	@RequestMapping(value="/isvalid", method = RequestMethod.POST)
	public IsValidResponseJson isvalid(@RequestParam("usercookie") String usercookie) {
		final IsValidResponseJson response = new IsValidResponseJson();
		if (usercookie.length() == 0) {
			response.setUsername("");
			response.setIsValid(false);
			return response;
		}
		// TODO: check that the cookie is valid and separate the username from the cookie
		response.setUsername("user");
		response.setIsValid(true);
		return response;
	}
}
package com.piccy.demo;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piccy.demo.domain.User;
import com.piccy.demo.service.LoginService;
import com.piccy.demo.Password;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// The LoginController class creates a new RSA key pair every time it (and by extension
// the server) is instantiated. This key pair is used to sign and verify user cookies.

@RestController
public class LoginController {
	private RSASigning rsaSigning;
	
	@PostConstruct
	public void initialize() {
		rsaSigning = new RSASigning();
	}
	
	@Autowired
	private LoginService loginService;
	
	public class RSASigning {
		private KeyPair pair;
		
	    public RSASigning() {
	    	if (pair != null) {
	    		return;
	    	}
			try {
				KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
				generator.initialize(2048, new SecureRandom());
		        KeyPair newPair = generator.generateKeyPair();
		        pair = newPair;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public String sign(String plainText) throws Exception {
	        Signature privateSignature = Signature.getInstance("SHA256withRSA");
	        System.out.println(pair);
	        System.out.println(privateSignature);
	        privateSignature.initSign(pair.getPrivate());
	        privateSignature.update(plainText.getBytes("UTF-8"));

	        byte[] signature = privateSignature.sign();

	        return Base64.getEncoder().encodeToString(signature);
	    }

	    public boolean verify(String plainText, String signature) throws Exception {
	        Signature publicSignature = Signature.getInstance("SHA256withRSA");
	        publicSignature.initVerify(pair.getPublic());
	        publicSignature.update(plainText.getBytes("UTF-8"));

	        byte[] signatureBytes = Base64.getDecoder().decode(signature);

	        return publicSignature.verify(signatureBytes);
	    }
	}
	
	public class RegisterReponseJson {
		private boolean success;
		private String username;
		private int userid;
		private String error;
		
		public boolean getSuccess() {
			return success;
		}
		
		public void setSuccess(boolean newValue) {
			success = newValue;
		}
		
		public String getUsername() {
			return username;
		}
		
		public void setUsername(String newValue) {
			username = newValue;
		}
		
		public String getError() {
			return error;
		}
		
		public void setError(String newValue) {
			error = newValue;
		}
		
		public int getUserid() {
			return userid;
		}
		
		public void setUserid(int newValue) {
			userid = newValue;
		}
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RegisterReponseJson register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
		final RegisterReponseJson response = new RegisterReponseJson();

		if (loginService.usernameExists(username)) {
			response.setSuccess(false);
			response.setError("User already exists with that username.");
			return response;
		}
		
		User user = new User();
		user.setUsername(username);
		String saltedHashed;
		try {
			saltedHashed = Password.getSaltedHash(password);
			user.setPassword(saltedHashed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(false);
			response.setError("Unable to hash and salt password.");
		}
		user.setEmail(email);
		
		loginService.createUser(user);
		
		response.setSuccess(true);
		response.setUserid(user.getUserid());
		response.setUsername(username);
		response.setError("");
		return response;
	}
	
	public class LoginResponseJson
	{
		private String userCookie;
		private Boolean success;
		private int userid;
		
		public String getUserCookie() {
			return userCookie;
		}
		
		public void setUserid(int userid) {
			this.userid = userid;
		}
		
		public int getUserid() {
			return userid;
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
		
	// Returns a JSOON response indicating the success of the request and, if successful, the cookie
	// for the now logged in user. The cookie is currently in the form <signature>:<username> where
	// the signature is a base64 encoded signature on the users plaintext.
    @CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponseJson login(@RequestParam("username") String username, @RequestParam("password") String password) {
		final LoginResponseJson response = new LoginResponseJson();
		// Get user from database.
		User user = loginService.getUserByName(username);
		if (user == null) {
			response.setSuccess(false);
			return response;
		}
		if (!username.equals(user.getUsername())) {
			response.setSuccess(false);
			return response;
		}
		boolean check = false;
		try {
			check = Password.check(password, user.getPassword());
		} catch (Exception e) {
			System.out.println(e);
		}
		if (check == false) {
			response.setSuccess(false);
			return response;
		}
		String signature = "";
		try {
			signature = rsaSigning.sign(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(false);
			return response;
		}
		// TODO: encrypt username?
		String usercookie = new String(signature + ":" + username);
		response.setUserCookie(usercookie);
		response.setUserid(user.getUserid());
		response.setSuccess(true);
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
	
	// Returns a JSON response containing an "isvalid" field with a boolean of whether the 
	// the given cookie is valid and a "username" field specifying the username that the
	// cookie belongs to.
	@RequestMapping(value="/isvalid", method = RequestMethod.POST)
	public IsValidResponseJson isvalid(@RequestParam("usercookie") String usercookie) {
		final IsValidResponseJson response = new IsValidResponseJson();
		if (usercookie.length() == 0) {
			response.setUsername("");
			response.setIsValid(false);
			return response;
		}
		String[] parts = usercookie.split(":");
		String username = parts[1];
		System.out.println(username);
		String cookie = parts[0];
		System.out.println(cookie);
		try {
			if (rsaSigning.verify(username, cookie)) {
				response.setUsername(username);
				response.setIsValid(true);
				System.out.println("In here!");
				return response;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		response.setUsername("");
		response.setIsValid(false);
		return response;
	}
}
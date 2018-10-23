package com.piccy.demo;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class LoginController {
	private RSASigning rsaSigning;
	
	@PostConstruct
	public void initialize() {
		rsaSigning = new RSASigning();
	}
	
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
		// TODO: return an actual value that can be used for a cookie
		if (username.equals("user") && password.equals("password")) {
			String signature = "";
			try {
				signature = rsaSigning.sign(username);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String usercookie = new String(signature + ":" + username);
			response.setUserCookie(usercookie);
			response.setSuccess(true);
			return response;
		}
		response.setSuccess(false);
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
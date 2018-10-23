package com.piccy.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.piccy.demo.service.FileResponse;
import com.piccy.demo.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.piccy.demo.service.filestorage")
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private FileStorageService fileStorageService;
	private final AtomicLong counter = new AtomicLong();

	
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public FileResponse upload(@RequestParam("userid") String userid, @RequestParam("file") MultipartFile file) {
		String filename = fileStorageService.storeFile(file);
		//TODO: generate filename for storage buckets
		String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();

		return new FileResponse(filename, downloadUri, file.getContentType(), file.getSize());
	}


	@RequestMapping(value = "/images/{filename:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(@PathVariable String filename, HttpServletRequest request) {

		Resource resource = fileStorageService.loadFile(filename);
		String contentType = null;

		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}
		catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
        		.contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}



}

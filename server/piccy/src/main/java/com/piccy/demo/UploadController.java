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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("com.piccy.demo.service.filestorage")
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	private final AtomicLong counter = new AtomicLong();
	
	
	
	private static final String template = "Hello %s!";
	
	@RequestMapping("/greeting")
	public Dummy greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Dummy(counter.incrementAndGet(), String.format(template, name));
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public FileResponse upload(@RequestParam("userid") String userid, @RequestParam("file") MultipartFile file) {
		String filename = fileStorageService.storeFile(file);
		//TODO: generate filename
		System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()); 
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

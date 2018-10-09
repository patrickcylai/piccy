package com.piccy.demo.service;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;



@Service
@ComponentScan("com.piccy.demo")
public class FileStorageService {
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	
	private final Path rootStorage;;
	
	
	
	/*
	 * Stores file in local filesystem
	 */
	public String storeFile(MultipartFile file) {
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());	
		try {
			
			Path storeLocation = this.rootStorage.resolve(filename);
			Files.copy(file.getInputStream(), storeLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return filename;
			
		}
		catch (Exception ex) {
			throw new RuntimeException ("Failed to store file  " +  filename + ".");
		}
	}
	
	
	/*
	 * loads file of the given filename
	 */
	public Resource loadFile(String filename) {
		try {
			Path filepath = this.rootStorage.resolve(filename).normalize();
			
			Resource resource = new UrlResource(filepath.toUri());
			if (resource.exists()) {
				return resource;
			}
			else {
				throw new RuntimeException("File not found " +  filename);
			}
		}
		catch (Exception ex) {
			throw new RuntimeException("File not found " +  filename);
		}
	}
	

	
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootStorage.toFile());
	}
	
	
	
	/*
	 * contructor method
	 */
    //
	@Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        /*this.rootStorage = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.rootStorage);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }*/
		
		this.rootStorage = Paths.get("./uploads").toAbsolutePath().normalize();
		 try {
	            Files.createDirectories(this.rootStorage);
	        } catch (Exception ex) {
	            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
    }

}

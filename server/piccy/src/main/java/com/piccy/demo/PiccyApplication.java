package com.piccy.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.piccy.demo.service.*;
import com.piccy.demo.service.filestorage.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class PiccyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiccyApplication.class, args);
	}
}

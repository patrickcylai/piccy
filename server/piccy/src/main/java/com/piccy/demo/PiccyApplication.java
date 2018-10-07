package com.piccy.demo;

import com.piccy.demo.service.filestorage.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class PiccyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiccyApplication.class, args);
	}
}

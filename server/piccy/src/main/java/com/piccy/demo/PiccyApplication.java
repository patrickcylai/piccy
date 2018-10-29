package com.piccy.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.piccy.demo.responses.FileStorageProperties;
import com.piccy.demo.service.*;


// @SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({
    FileStorageProperties.class
})

@SpringBootApplication
public class PiccyApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PiccyApplication.class, args);
	}
}



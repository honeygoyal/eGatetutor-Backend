package com.tutor.egate;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tutor.egate.controller.UploadController;

@SpringBootApplication
public class EgateTutorBackendApplication {

	public static void main(String[] args) {
		new File(UploadController.uploadDirectory).mkdir();
		SpringApplication.run(EgateTutorBackendApplication.class, args);
	}

	
	  @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	  BCryptPasswordEncoder(); }
	 
}

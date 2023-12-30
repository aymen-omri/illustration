package com.illustration.illustrationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class IllustrationProjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(IllustrationProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IllustrationProjectApplication.class);
	}

}

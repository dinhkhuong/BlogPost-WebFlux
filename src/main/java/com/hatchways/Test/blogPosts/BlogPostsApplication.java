package com.hatchways.Test.blogPosts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BlogPostsApplication {

	@Bean
	public WebClient.Builder webCB(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogPostsApplication.class, args);
	}



}

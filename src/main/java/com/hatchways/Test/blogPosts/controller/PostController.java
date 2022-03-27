package com.hatchways.Test.blogPosts.controller;

import com.hatchways.Test.blogPosts.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class PostController {
    @Autowired
    private WebClient.Builder webCB;

    @PostMapping(path = "tech")
    public Posts insertData(){
        return webCB.build()
            .get()
            .uri("https://api.hatchways.io/assessment/blog/posts?tag=tech")
            .retrieve()
            .bodyToMono(Posts.class)
            .block();
    }
}

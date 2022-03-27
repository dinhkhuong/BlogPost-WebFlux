package com.hatchways.Test.blogPosts.controller;

import com.hatchways.Test.blogPosts.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private WebClient.Builder webCB;

    List<String> tag = Arrays.asList("tech","health");

    @GetMapping(path = "tech")
    public Flux<Posts> insertData(){
        /*return webCB.build()
            .get()
            .uri("https://api.hatchways.io/assessment/blog/posts?tag=tech")
            .retrieve()
            .bodyToMono(Posts.class)
            .block();*/

        Flux<Posts> posts = Flux.fromIterable(tag)
                .flatMap(x->{
                    Mono<Posts> post =
                            webCB.build()
                            .get()
                            .uri("https://api.hatchways.io/assessment/blog/posts?tag={tag}",x)
                            .retrieve()
                            .bodyToMono(Posts.class);
                    return post;
                });
        return posts;
    }
}

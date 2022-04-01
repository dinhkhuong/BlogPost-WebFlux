package com.hatchways.Test.blogPosts.controller;

import com.hatchways.Test.blogPosts.entities.Post;
import com.hatchways.Test.blogPosts.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class PostController {
    @Autowired
    private WebClient.Builder webCB;

    List<String> tag = Arrays.asList("tech","health");

    Posts po = new Posts();
    @GetMapping(path = "tech")
    public Mono<Posts> insertData(){

        Flux<Post> posts = Flux.fromIterable(tag)
                .flatMap(x->{
                    List<Post> post =
                            webCB.build()
                            .get()
                            .uri("https://api.hatchways.io/assessment/blog/posts?tag={tag}",x)
                            .retrieve()
                            .bodyToMono(Posts.class)
                            .block()
                            .getPost();

                    return Flux.fromIterable(post);

                })
                //remove duplicates
                .distinct(Post::getID);
        List toPost = StreamSupport
                .stream(posts.toIterable().spliterator(), false)
                .collect(Collectors.toList());
        po.setPost(toPost);
        return Mono.just(po);

    }
}

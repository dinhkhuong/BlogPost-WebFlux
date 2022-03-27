package com.hatchways.Test.blogPosts.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Posts {
    @JsonProperty("posts")
    List<Post> post;
    /*
    {
    "posts": [
        {
            "author": "Rylee Paul",
            "authorId": 9,
            "id": 1,
            "likes": 960,
            "popularity": 0.13,
            "reads": 50361,
            "tags": [
                "tech",
                "health"
            ]
        },
        ...
        * */

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}

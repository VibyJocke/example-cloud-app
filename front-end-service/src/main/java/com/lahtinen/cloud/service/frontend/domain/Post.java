package com.lahtinen.cloud.service.frontend.domain;

public class Post {

    private final PostId id;
    private final String title;
    private final String body;

    public Post(String title, String body) {
        id = PostId.create();
        this.title = title;
        this.body = body;
    }

    public PostId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

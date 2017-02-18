package com.lahtinen.cloud.service.frontend.domain;

public class Post {

    private final PostId id;
    private final String title;
    private final String body;

    private Post(PostId id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static Post create(String title, String body) {
        return new Post(PostId.create(), title, body);
    }

    public static Post parse(PostId id, String title, String body) {
        return new Post(id, title, body);
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

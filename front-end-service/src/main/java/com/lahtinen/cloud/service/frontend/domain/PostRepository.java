package com.lahtinen.cloud.service.frontend.domain;

import java.util.List;

public interface PostRepository {
    List<Post> getPosts();

    Post getPost(PostId id);

    void store(Post post);
}

package com.lahtinen.cloud.service.frontend.domain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> getPosts();

    Optional<Post> getPost(PostId id);

    void store(Post post);
}

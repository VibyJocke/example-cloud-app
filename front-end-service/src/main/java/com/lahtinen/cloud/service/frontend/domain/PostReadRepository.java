package com.lahtinen.cloud.service.frontend.domain;

import java.util.Collection;
import java.util.Optional;

public interface PostReadRepository {
    Collection<Post> getPosts();

    Optional<Post> getPost(PostId id);

    Collection<Post> findPostByTitle(String titlePart);
}

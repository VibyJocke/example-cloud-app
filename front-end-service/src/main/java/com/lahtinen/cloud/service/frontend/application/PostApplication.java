package com.lahtinen.cloud.service.frontend.application;

import com.lahtinen.cloud.service.frontend.domain.EventPublisher;
import com.lahtinen.cloud.service.frontend.domain.Post;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.domain.PostReadRepository;

import java.util.Collection;
import java.util.Optional;

public class PostApplication {

    private final EventPublisher eventPublisher;
    private final PostReadRepository postReadRepository;

    public PostApplication(EventPublisher eventPublisher, PostReadRepository postReadRepository) {
        this.eventPublisher = eventPublisher;
        this.postReadRepository = postReadRepository;
    }

    public Collection<Post> getPosts() {
        return postReadRepository.getPosts();
    }

    public Optional<Post> getPost(String id) {
        return postReadRepository.getPost(PostId.parse(id));
    }

    public PostId createPost(String title, String body) {
        // TODO: Put event on SQS
        return PostId.create();
    }

    public void deletePost(String id) {
        // TODO: Put event on SQS
    }
}

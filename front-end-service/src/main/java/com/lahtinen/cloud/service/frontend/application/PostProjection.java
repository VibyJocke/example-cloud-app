package com.lahtinen.cloud.service.frontend.application;

import com.google.common.eventbus.Subscribe;
import com.lahtinen.cloud.service.frontend.domain.Post;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.domain.PostReadRepository;
import com.lahtinen.cloud.service.frontend.port.queue.event.PostCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class PostProjection implements PostReadRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostProjection.class);

    private final Map<PostId, Post> posts = new ConcurrentHashMap<>();

    @Override
    public Collection<Post> getPosts() {
        return posts.values();
    }

    @Override
    public Optional<Post> getPost(PostId id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public Collection<Post> findPostByTitle(String titlePart) {
        return posts.values().stream()
                .filter(post -> post.getTitle().contains(titlePart))
                .collect(toList());
    }

    @Subscribe
    public void event(PostCreatedEvent event) {
        LOGGER.info("Got post with id {}", event.getId());
        posts.put(event.getId(), Post.parse(event.getId(), event.getTitle(), event.getBody()));
    }
}

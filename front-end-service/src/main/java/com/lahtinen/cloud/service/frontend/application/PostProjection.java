package com.lahtinen.cloud.service.frontend.application;

import com.google.common.eventbus.Subscribe;
import com.lahtinen.cloud.service.frontend.domain.Post;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.domain.PostReadRepository;
import com.lahtinen.cloud.service.frontend.port.rest.queue.event.PostCreatedEvent;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostProjection implements PostReadRepository {

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
    public void store(Post post) {

    }

    @Subscribe
    public void event(PostCreatedEvent event) {
        posts.put(event.getId(), Post.parse(event.getId(), event.getTitle(), event.getBody()));
    }
}

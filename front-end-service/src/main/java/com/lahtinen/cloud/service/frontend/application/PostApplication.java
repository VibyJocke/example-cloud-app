package com.lahtinen.cloud.service.frontend.application;

import com.lahtinen.cloud.service.frontend.domain.CommandPublisher;
import com.lahtinen.cloud.service.frontend.domain.Post;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.domain.PostReadRepository;
import com.lahtinen.cloud.service.frontend.port.queue.command.CreatePostCommand;

import java.util.Collection;
import java.util.Optional;

public class PostApplication {

    private final CommandPublisher commandPublisher;
    private final PostReadRepository postReadRepository;

    public PostApplication(CommandPublisher commandPublisher, PostReadRepository postReadRepository) {
        this.commandPublisher = commandPublisher;
        this.postReadRepository = postReadRepository;
    }

    public Collection<Post> getPosts() {
        return postReadRepository.getPosts();
    }

    public Optional<Post> getPost(String id) {
        return postReadRepository.getPost(PostId.parse(id));
    }

    public PostId createPost(String title, String body) {
        commandPublisher.publish(new CreatePostCommand(title, body));
        return PostId.create();
    }

    public void deletePost(String id) {
        // TODO: Put event on SQS
    }
}

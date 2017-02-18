package com.lahtinen.cloud.service.frontend.application;

import com.lahtinen.cloud.service.frontend.domain.Post;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.domain.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostApplication {

    private PostRepository postRepository;

    public PostApplication(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }

    public Optional<Post> getPost(String id) {
        return postRepository.getPost(PostId.parse(id));
    }

    public PostId createPost(String title, String body) {
        final Post post = new Post(title, body);
        postRepository.store(post);
        return post.getId();
    }
}

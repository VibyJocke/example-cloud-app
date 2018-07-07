package com.lahtinen.cloud.service.frontend.port.rest;

import com.lahtinen.cloud.service.frontend.application.PostApplication;
import com.lahtinen.cloud.service.frontend.port.rest.request.CreatePostRequest;
import com.lahtinen.cloud.service.frontend.port.rest.response.CreatePostResponse;
import com.lahtinen.cloud.service.frontend.port.rest.response.PostResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("v1/post")
public class PostResourceV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostResourceV1.class);

    private final PostApplication postApplication;

    public PostResourceV1(PostApplication postApplication) {
        this.postApplication = postApplication;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts() {
        LOGGER.info("Get posts");
        var response = postApplication.getPosts().stream()
                .map(PostResponse::new)
                .collect(toList());
        return Response.ok(response).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPosts(@PathParam("id") String id) {
        LOGGER.info("Get post {}", id);
        var post = postApplication.getPost(id);
        if (!post.isPresent()) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(new PostResponse(post.get())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPost(@Valid CreatePostRequest request) {
        LOGGER.info("Create post");
        var id = postApplication.createPost(request.getTitle(), request.getBody());
        return Response.ok(new CreatePostResponse(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") String id) {
        LOGGER.info("Delete post {}", id);
        postApplication.deletePost(id);
        return Response.noContent().build();
    }
}

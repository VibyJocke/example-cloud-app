package com.lahtinen.cloud.service.frontend.port.rest;

import com.lahtinen.cloud.service.frontend.application.PostApplication;
import com.lahtinen.cloud.service.frontend.port.rest.request.CreatePostRequest;
import com.lahtinen.cloud.service.frontend.port.rest.response.CreatePostResponse;
import com.lahtinen.cloud.service.frontend.port.rest.response.PostResponse;

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

@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    private static final String VERSION_1_MIME_TYPE = "application/json+v1";
    private final PostApplication postApplication;

    public PostResource(PostApplication postApplication) {
        this.postApplication = postApplication;
    }

    @GET
    @Produces(VERSION_1_MIME_TYPE)
    public Response getPosts() {
        var response = postApplication.getPosts().stream()
                .map(PostResponse::new)
                .collect(toList());
        return Response.ok(response).build();
    }

    @GET
    @Path("{id}")
    @Produces(VERSION_1_MIME_TYPE)
    public Response getPosts(@PathParam("id") String id) {
        var post = postApplication.getPost(id);
        if (!post.isPresent()) {
            return Response.status(NOT_FOUND).build();
        }
        return Response.ok(new PostResponse(post.get())).build();
    }

    @POST
    @Produces(VERSION_1_MIME_TYPE)
    public Response getPosts(@Valid CreatePostRequest request) {
        var id = postApplication.createPost(request.getTitle(), request.getBody());
        return Response.ok(new CreatePostResponse(id)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") String id) {
        postApplication.deletePost(id);
        return Response.ok().build();
    }
}

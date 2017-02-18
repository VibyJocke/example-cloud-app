package com.lahtinen.cloud.service.frontend.port.rest;

import com.lahtinen.cloud.service.frontend.application.PostApplication;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    private static final String VERSION_1_MIME_TYPE = "application/json+v1";
    private PostApplication postApplication;

    public PostResource(PostApplication postApplication) {
        this.postApplication = postApplication;
    }

    @GET
    @Produces(VERSION_1_MIME_TYPE)
    public Response getPosts() {
        return Response.ok(postApplication.getPosts()).build();
    }

    @GET
    @Path("{id}")
    @Produces(VERSION_1_MIME_TYPE)
    public Response getPosts(@PathParam("id") String id) {
        return Response.ok(postApplication.getPost(id)).build();
    }
}

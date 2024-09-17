package org.soulsheart.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soulsheart.requests.FollowRequest;
import org.soulsheart.services.FollowerService;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerController {

    @Inject
    private FollowerService followerService;

    @PUT
    public Response putFollowUser(@PathParam("userId") String userId, FollowRequest request) {
        return followerService.updateFollowUser(userId, request);
    }

    @GET
    public Response getFollowers(@PathParam("userId") String userId){
        return followerService.retrieverFollowers(userId);
    }
}

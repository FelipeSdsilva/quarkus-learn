package org.soulsheart.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.soulsheart.dto.UserDTO;
import org.soulsheart.requests.UserRequest;
import org.soulsheart.services.UserService;

import java.net.URI;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;


    @GET
    public Response getAllUsersListed() {
        return Response.ok(userService.retrieverAllUsersListed()).build();
    }


    @GET
    @Path("/{id}")
    public Response getUserPerId(ObjectId id) {
        return Response.ok(userService.retrieverCustomerPerId(id)).build();
    }

    @POST
    public Response postNewUser(UserRequest request) {
        UserDTO userDTO = userService.insertNewUser(request);
        return Response.created(URI.create("/users/" + userDTO.getId())).entity(userDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response putCustomerPerId(ObjectId id, UserRequest request) {
        UserDTO userDTO = userService.updateUserPerId(id, request);
        return Response.ok(userDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUserPerId(ObjectId id) {
        userService.deleteUserPerId(id);
        return Response.noContent().build();
    }
}
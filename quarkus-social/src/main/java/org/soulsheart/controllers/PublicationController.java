package org.soulsheart.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.soulsheart.dto.PublicationDTO;
import org.soulsheart.requests.PublicationRequest;
import org.soulsheart.services.PublicationService;

import java.net.URI;
import java.util.List;

@Path("/users/{userId}/publications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PublicationController {


    @Inject
    public PublicationService publicationService;

    @POST
    public Response createPublication(@PathParam("userId") String userId, PublicationRequest request) {
        PublicationDTO publication = publicationService.createPublicationForUser(userId, request);
        return Response.created(URI.create("/users/" + publication.getUser().getId() + "/publications/" + publication.getId()))
                .entity(publication).build();
    }

    @GET
    public Response getPublicationsByUser(@PathParam("userId") String userId) {
        List<PublicationDTO> publications = publicationService.getPublicationsByUser(userId);
        return Response.ok(publications).build();
    }
}

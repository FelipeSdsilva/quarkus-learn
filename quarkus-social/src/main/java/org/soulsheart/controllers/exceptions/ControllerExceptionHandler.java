package org.soulsheart.controllers.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ControllerExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            WebApplicationException webEx = (WebApplicationException) exception;
            return Response.status(webEx.getResponse().getStatus())
                    .entity(new ErrorResponse(webEx.getMessage(), webEx.getResponse().getStatus()))
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse("Internal Server Error", Response.Status.BAD_REQUEST.getStatusCode()))
                .build();
    }
}

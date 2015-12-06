package de.griesser.rest.exceptions;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFound> {

    @Override
    public Response toResponse(PersonNotFound ex) {
        return Response.status(NOT_FOUND).entity(ex.getMessage()).build();
    }

}

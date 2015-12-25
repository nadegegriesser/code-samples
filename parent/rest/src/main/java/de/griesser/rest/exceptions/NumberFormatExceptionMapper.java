package de.griesser.rest.exceptions;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NumberFormatExceptionMapper implements ExceptionMapper<NumberFormatException> {

    @Override
    public Response toResponse(NumberFormatException ex) {
        return Response.status(BAD_REQUEST).entity("NumberFormatException " + ex.getMessage()).build();
    }

}

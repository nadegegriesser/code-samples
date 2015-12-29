package de.griesser.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;

@StatusCode
public class StatusCodeFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) throws IOException {
        if (respCtx.getStatus() == Status.OK.getStatusCode()) {
            respCtx.setStatus(Status.CREATED.getStatusCode());
        }
    }

}

package de.griesser.rest.services;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;

@Path("/persons")
public interface PersonService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	Collection<PersonResource> getAll();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	PersonResource get(@PathParam(value = "id") int id) throws PersonNotFound;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	PersonResource create(PersonResource person);

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	PersonResource update(@PathParam(value = "id") int id, PersonResource person) throws PersonNotFound;

	@DELETE
	@Path("/{id}")
	void delete(@PathParam(value = "id") int id) throws PersonNotFound;

}

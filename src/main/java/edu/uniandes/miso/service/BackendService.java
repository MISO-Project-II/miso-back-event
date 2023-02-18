package edu.uniandes.miso.service;

import edu.uniandes.miso.repository.EventRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("backend-event")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BackendService {

	@Inject
	EventRepository repository;

	@POST
	@Path(("listById"))
	public Response getListById(List<Long> list) {
		return Response.ok(repository.findAllById(list)).build();
	}

	@GET
	@Path("{id}")
	public Response getListByUserId(@PathParam("id") Long idUser) {
		return Response.ok(repository.findByIdUserCreator(idUser)).build();
	}

}

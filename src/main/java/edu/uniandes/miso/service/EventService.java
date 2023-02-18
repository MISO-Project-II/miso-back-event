package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.Input;
import edu.uniandes.miso.dto.ResponseService;
import edu.uniandes.miso.entity.Event;
import edu.uniandes.miso.repository.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("event")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventService {
	@Inject
	Logger log;
	@Inject
	EventRepository repository;

	ResponseService responseService = new ResponseService();

	@POST
	public Response create(Input input) {
		if (StringUtils.isNotEmpty(input.getName())) {
			Event event = new Event();
			putData(input, event);
			responseService.setMessage("Created");
			responseService.setResult(repository.save(event));
			return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Fail to created");
		return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") Long idService) {
		Optional<Event> findEvent = getEvent(idService);
		if(findEvent.isPresent()){
			responseService.setSuccess(true);
            responseService.setMessage("success");
            responseService.setResult(findEvent.get());
            return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(true);
		responseService.setMessage("Not found");
		return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long idEvent, Input input) {
		Optional<Event> findEvent = getEvent(idEvent);
		if(findEvent.isPresent()){
			Event event = findEvent.get();
			putData(input, event);
			responseService.setMessage("Updated");
            responseService.setResult(repository.save(event));
            return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Fail to update");
		return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
	}

	@GET
	@Path("{id}")
	public Response delete(@PathParam("id") Long idEvent) {
		Optional<Event> event = getEvent(idEvent);
		if(event.isPresent()){
			repository.deleteById(event.get().getIdEvent());
            responseService.setMessage("Deleted");
            return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Fail to update");
		return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
	}

	@GET
	public Response getAll() {
        responseService.setSuccess(true);
        responseService.setMessage("success");
		responseService.setResult(repository.findAll());
        return Response.status(Response.Status.OK).entity(responseService).build();
	}
	private void putData(Input input, Event event) {
		event.setName(input.getName());
		event.setDate(input.getDate());
		event.setDescription(input.getDescription());
		event.setCity(input.getCity());
		event.setIdSport(input.getIdSport());
		event.setIdUserCreator(input.getIdUserCreator());
		event.setContract(input.getEvenType());
		responseService.setSuccess(true);
	}
	private Optional<Event> getEvent(Long idEvent) {
		Optional<Event> findEvent = repository.findById(idEvent);
		return findEvent;
	}
}

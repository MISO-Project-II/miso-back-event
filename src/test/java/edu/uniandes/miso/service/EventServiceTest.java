package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.Input;
import edu.uniandes.miso.entity.Event;
import edu.uniandes.miso.repository.EventRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class EventServiceTest {

    @InjectMock
    EventRepository repository;
    @Inject
    EventService eventService;

    Event event;
    PodamFactory factory = new PodamFactoryImpl();
    @BeforeEach
    void setUp() {
        event = factory.manufacturePojo(Event.class);
    }

    @Test
    void create() {
        Mockito.when(repository.save(event)).thenReturn(event);
        Input inputServiceDto = factory.manufacturePojo(Input.class);
        Response response = eventService.create(inputServiceDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void createFail() {
        Mockito.when(repository.save(event)).thenReturn(event);
        Input inputServiceDto = factory.manufacturePojo(Input.class);
        inputServiceDto.setName(null);
        Response response = eventService.create(inputServiceDto);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void get() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(event));
        Response response = eventService.get(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getFail() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(event));
        Response response = eventService.get(51L);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    @Test
    void update() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(event));
        Mockito.when(repository.save(event)).thenReturn(event);
        Input inputServiceDto = factory.manufacturePojo(Input.class);
        Response response = eventService.update(1L,inputServiceDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void updateFail() {
        Mockito.when(repository.save(event)).thenReturn(event);
        Input inputServiceDto = factory.manufacturePojo(Input.class);
        Response response = eventService.update(1L,inputServiceDto);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }



    @Test
    void getAll() {
        List<Event> services = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(services);
        Response response = eventService.getAll();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    void delete() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(event));
        Response response = eventService.delete(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void deleteFail() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(event));
        Response response = eventService.delete(51L);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
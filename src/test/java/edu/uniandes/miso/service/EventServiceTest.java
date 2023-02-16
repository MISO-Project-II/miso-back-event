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
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}
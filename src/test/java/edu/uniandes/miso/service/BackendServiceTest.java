package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.Input;
import edu.uniandes.miso.dto.ResponseService;
import edu.uniandes.miso.entity.EvenType;
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

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BackendServiceTest {

    @InjectMock
    EventRepository repository;

    @Inject
    BackendService backendService;

    PodamFactory factory = new PodamFactoryImpl();
    Event event;

    @BeforeEach
    void setUp() {
        event = factory.manufacturePojo(Event.class);
        event.setContract(EvenType.INSIDE_OF_HOUSE);
    }
    @Test
    void getListById() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        Mockito.when(repository.findAll()).thenReturn(list);
        Response response = backendService.getListById(new ArrayList<>());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    void getListByUserId() {
        List<Event> list = new ArrayList<>();
        list.add(event);
        Mockito.when(repository.findByIdUserCreator(1L)).thenReturn(list);
        Response response = backendService.getListByUserId(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void dto() {
        Input input  = factory.manufacturePojo(Input.class);
        ResponseService responseService = factory.manufacturePojo(ResponseService.class);
        assertNotNull(input.toString());
        assertNotNull(responseService.toString());

    }
}
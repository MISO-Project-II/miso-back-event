package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByIdUserCreator(Long idUserCreator);

}

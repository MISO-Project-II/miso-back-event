package edu.uniandes.miso.repository;

import edu.uniandes.miso.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}

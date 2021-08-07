package tutorial.mvc.springMVC.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@Rollback(false)
class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("간단한 CRUD")
    void start_1() throws Exception{
        //given
        Event event = Event.createEvent("eventRegion", 10, "username");
        Event save = eventRepository.save(event);
        entityManager.flush();
        entityManager.clear();
        Event findEvent = eventRepository.findById(save.getId()).orElseGet(() -> event);

        assertEquals(save.getUsername(),event.getUsername());

        //then
    }

}
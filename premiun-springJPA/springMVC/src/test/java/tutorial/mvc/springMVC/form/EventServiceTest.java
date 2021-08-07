package tutorial.mvc.springMVC.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tutorial.mvc.springMVC.EventDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Test
    @DisplayName("service 테스트 초기")
    void start_1() throws Exception{
        //given
        EventDto.EventForm eventForm = EventDto.EventForm.of("username", 12, "eventRegion");
        Event event = Event.createEvent("eventRegion", 12, "username");
        given(eventRepository.save(any())).willReturn(event);
        //when
        EventDto.ConvertDto convertDto = eventService.create(eventForm);
        //then
        assertNotNull(convertDto);
        assertEquals(convertDto.getEventForm().getUsername(),event.getUsername());
    }

}
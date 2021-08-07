package tutorial.mvc.springMVC.form;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.mvc.springMVC.EventDto;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventDto.ConvertDto create(EventDto.EventForm eventForm) {
        Event event = eventForm.toEntity();
        Event save = eventRepository.save(event);
        return new EventDto.ConvertDto(save);
    }



}

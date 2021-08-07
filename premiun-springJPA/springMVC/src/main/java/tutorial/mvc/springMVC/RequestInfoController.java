package tutorial.mvc.springMVC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class RequestInfoController {

    @GetMapping("/events/v1")
    public String read(@RequestBody EventDto eventDto) {
        log.info("eventDto = {}", eventDto.toString());
        String name = eventDto.getName();
        return name;
    }

    @GetMapping("/events/v2")
    public EventDto readV3(EventDto eventDto) {
        return eventDto;
    }

    //TODO HttpEntity<B>를 사용하게 되면 http 요청에 대한 header 정보를 확인 할 수 있다
    @GetMapping("/events/v3")
    public HttpEntity<String> readV4(@ModelAttribute EventDto eventDto) {
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(eventDto.getEventCode());
        log.info("stringHttpEntity = {}",stringHttpEntity.toString());
        return stringHttpEntity;
    }

    @GetMapping("/events/v4/{id}")
    public Map<String, ?> readV5(@PathVariable("id") Integer id) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("id", id);
        objectObjectHashMap.put("username", "username"+id);
        return objectObjectHashMap;
    }



}

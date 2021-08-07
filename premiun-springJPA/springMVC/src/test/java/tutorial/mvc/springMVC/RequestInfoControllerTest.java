package tutorial.mvc.springMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RequestInfoController.class)
class RequestInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void beforeConfig(){
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("body 검증 테스트 코드")
    void start_1() throws Exception{
        EventDto eventDto = new EventDto("name", "eventCode", null);
        EventDto eventDtov1 = new EventDto("username", "eventCode", 12);

        String jsonEventDto = objectMapper.writeValueAsString(eventDto);
        String jsonEventDtov1 = objectMapper.writeValueAsString(eventDtov1);
        mockMvc.perform(get("/events/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEventDto)
        )
                .andDo(print())
                .andExpect(content().string("name"));

        mockMvc.perform(get("/events/v2")
                .param("name", "username")
                .param("eventCode", "eventCode")
                .param("age", "12"))
                .andDo(print())
                .andExpect(content().json(jsonEventDtov1));
    }

    //TODO  기존 controller 테스트 방식(JSON 데이터 비교) - 상황에 따라 알맞게 사용해야함
    // 비교 방식
    // content().json(실제로 생성한 객체를 objectMapper를 통해서 json을 변경한 데이터를 집어 넣어서 확인)
    // 변경 방식
    // jsonPath("json키값").value(기대값)을 통해서 비교하는 방식 존재
    @Test
    @DisplayName("PathVariable 테스트 코드")
    void start_2() throws Exception{

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("id", 1);
        objectObjectHashMap.put("username", "username1");

        String s = objectMapper.writeValueAsString(objectObjectHashMap);

        mockMvc.perform(get("/events/v4/1"))
                .andDo(print())
                .andExpect(content().json(s));

        mockMvc.perform(get("/events/v4/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("username").value("username1"));
    }



}
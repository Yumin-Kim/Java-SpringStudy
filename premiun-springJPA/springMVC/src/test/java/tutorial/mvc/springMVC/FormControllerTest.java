package tutorial.mvc.springMVC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tutorial.mvc.springMVC.form.EventService;
import tutorial.mvc.springMVC.form.FormController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FormController formController;

    @MockBean
    private EventService eventService;

    @Test
    @DisplayName("Form데이터 처리 테스트 코드")
    void start_3() throws Exception {
        EventDto.EventForm formData = EventDto.EventForm.of("username1", 12, "123");
        EventDto.ConvertDto convertDto = new EventDto.ConvertDto(formData);
        mockMvc.perform(post("/events/form/result")
                .queryParam("username", "username1")
                .queryParam("age", "12")
                .queryParam("eventRegion", "123")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Form 데이터 처리 및 Validation 진행")
    void start_2() throws Exception {
        //given
        EventDto.EventFormV2 eventForm = EventDto.EventFormV2.of("", 0, "");
        mockMvc.perform(MockMvcRequestBuilders.post("/events/form/get")
//                .queryParam("username", "")
//                .queryParam("age", "1")
                .queryParam("eventRegion", "asd"))
                .andDo(print());
//                .andExpect()
        //when

        //then
    }

}
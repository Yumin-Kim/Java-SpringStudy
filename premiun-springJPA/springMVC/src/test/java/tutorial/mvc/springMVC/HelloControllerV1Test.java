package tutorial.mvc.springMVC;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class HelloControllerV1Test {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("테스트 코드")
    void start_1() throws Exception {
        mockMvc.perform(get("/getv1")
                .contentType(MediaType.APPLICATION_JSON) // TODO content-header 요청 당시 Header
                .accept(MediaType.APPLICATION_JSON) // TODO accept-header 응답 당시 Header
        )
                .andDo(print())
                .andExpect(content().string("Hello"));
    }

    @Test
    @DisplayName("HTTP Method중 Head , OPTIONS 통한 테스트 코드")
    void start_2() throws Exception {
        mockMvc.perform(head("/head")
        ).andDo(print());
//        .andExpect(content().string("Head Method"));

        mockMvc.perform(options("/head"))
                .andDo(print())
                .andExpect(header().stringValues(HttpHeaders.ALLOW,"GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS"));
    }
}
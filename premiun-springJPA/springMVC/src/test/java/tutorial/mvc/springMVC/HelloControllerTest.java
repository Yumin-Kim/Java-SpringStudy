package tutorial.mvc.springMVC;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("테스트")
    public void start_1() throws Exception{
        mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed());
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get("/hello1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
        mockMvc.perform(get("/apple"))
                .andExpect(content().string("apple"));
        mockMvc.perform(get("/apple/1"))
                .andExpect(content().string("apple"));
        mockMvc.perform(get("/apple/1/2"))
                .andExpect(content().string("apple"));

        mockMvc.perform(get("/regex/apple"))
                .andExpect(content().string("regex_test apple_123"));

    }

}
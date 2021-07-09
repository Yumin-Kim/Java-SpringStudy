package springcore.fastcamp.fastcamp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springcore.fastcamp.fastcamp.controller.HelloController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppConfigTest {

    @Autowired
    private HelloController helloController;

    private MockMvc mockMvc;

    @Test
    @DisplayName("MVC 테스트 모듈")
    void HelloWordTest() {
        //given
        System.out.println("Hello");
        //when

        //then
        org.junit.jupiter.api.Assertions.assertNotNull(helloController);

    }

    @Test
    @DisplayName("Test")
    void MockMVCTest_Starter() throws Exception {
        //given
        MockMvc build = MockMvcBuilders.standaloneSetup(helloController).build();
        //when
        //then

        build.perform(
                MockMvcRequestBuilders.get("/api/header")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"resultCode\":\"ok\",\"data\":null,\"description\":\"Ok\",\"transaction_time\":null}"));
    }

    @Test
    @DisplayName("Post Method")
    void PostMehodRequest() throws Exception {
        //given
        MockMvc build = MockMvcBuilders.standaloneSetup(helloController).build();
        //when
        build.perform(
                MockMvcRequestBuilders.post("/api/test")
        ).andDo(MockMvcResultHandlers.print());
        //then
    }

}
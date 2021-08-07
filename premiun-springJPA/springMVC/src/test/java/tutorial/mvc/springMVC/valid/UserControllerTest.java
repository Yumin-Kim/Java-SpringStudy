package tutorial.mvc.springMVC.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("ReponseEntity & HttpEntity Test Code")
    void start_1() throws Exception{
        mockMvc.perform(get("/users/response/entity")
                .param("username", "username")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\":\"username\"\n" +
                        "}")
        )
                .andDo(print());
    }

    @Test
    @DisplayName("Validation V1")
    void start_2() throws Exception{
        //given

        //when
        mockMvc.perform(post("/valid/query/v1")
                .queryParam("username", "username")
                .queryParam("age", "101")
        )
                .andDo(print());

        //then
    }

}
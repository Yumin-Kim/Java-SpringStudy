package tutorial.mvc.springMVC.valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class})
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("ReponseEntity & HttpEntity Test Code")
    void start_1() throws Exception {
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
    void start_2() throws Exception {
        mockMvc.perform(get("/valid/query/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username","username")
        )
                .andDo(print());
    }

    @Test
    @DisplayName("validation V2_ body")
    void start_3() throws Exception {
        //given
        UserDto userDto = new UserDto("username", 123);
        String s = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(get("/user/body/valid/v2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s)
        )
                .andDo(print());
        //when

        //then
    }

}
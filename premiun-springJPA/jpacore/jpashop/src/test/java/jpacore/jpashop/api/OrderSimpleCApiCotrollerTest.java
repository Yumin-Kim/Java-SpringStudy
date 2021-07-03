package jpacore.jpashop.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderSimpleCApiCotrollerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("MVC 첫 테스트")
    public void findAllOrder() throws Exception{
        //given
//        mvc.perform(MockMvcRequestBuilders.get("/api/v2/simple-orders").accept(MediaType.APPLICATION_JSON))
        //when

        //then
    }

}
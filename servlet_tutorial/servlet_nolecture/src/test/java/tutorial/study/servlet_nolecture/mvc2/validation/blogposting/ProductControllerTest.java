package tutorial.study.servlet_nolecture.mvc2.validation.blogposting;

import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ProductController.class)
@Slf4j
class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @Mock

}
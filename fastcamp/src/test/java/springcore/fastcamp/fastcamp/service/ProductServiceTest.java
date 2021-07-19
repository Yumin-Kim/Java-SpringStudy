package springcore.fastcamp.fastcamp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.repository.ProductRepository;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUserRepository userRepository;

    @Test
    @DisplayName("상품 조회")
    void findProduct() throws Exception{
        //given

        //when

        //then
    }



}
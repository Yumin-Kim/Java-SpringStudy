package springcore.fastcamp.fastcamp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.domin.ProductUser;
import springcore.fastcamp.fastcamp.domin.UserRole;
import springcore.fastcamp.fastcamp.dto.ProductMyPriceRequestDto;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;
import springcore.fastcamp.fastcamp.repository.ProductRepository;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductUserRepository productUserRepository;

    @Test
    @DisplayName("update Prouduct()에 의해 관심 가격이 3만원으로 변경되는지 확인")
    @Transactional
    @Rollback(value = false)
    void updateProduct_Normal() throws Exception{
        //given
        Long productId = 400L;
        int myprice = 30000;
        ProductMyPriceRequestDto requestMyPriceDto = new ProductMyPriceRequestDto(
                myprice
        );
        Long userId = 12345L;
        ProductRequestDto requestProductDto = new ProductRequestDto(
                "오리온 꼬북칩 초코츄러스맛 160g",
                "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg",
                "https://search.shopping.naver.com/gate.nhn?id=24161228524",
                2350
        );
        ProductService productService = new ProductService(productRepository, productUserRepository);
//
        ProductUser productUser = new ProductUser("Hello", "hello", "Helo", UserRole.USER);
        productUserRepository.save(productUser);
//        productUserRepository.findByname("Hello");
//        productUserRepository.findByname("Hello");
//        productUserRepository.findByname("Hello");
//        productUserRepository.findByname("Hello");
//        productUserRepository.findByname("Hello");

                Product product = new Product(requestProductDto);
        productRepository.createProduct(product,productUser);
        Product product1 = productRepository.getProduct(product.getId());
        System.out.println("product1.getTitle() = " + product1.getTitle());
        System.out.println("product1.getTitle() = " + product1.getTitle());
        System.out.println("product1.getTitle() = " + product1.getTitle());
        System.out.println("product1.getTitle() = " + product1.getTitle());
        System.out.println("product1.getTitle() = " + product1.getTitle());

        ////        Product product2 = productRepository.getProduct(productId);
//        productRepository.createProduct(product,productUser);
//        //        given(productRepository.findById(productId))
////                .willReturn(Optional.of(product));
//        //when
//        productRepository.updateProductMyPrice(productId, myprice);
//        Product product1 = productService.updateProduct(productId, requestMyPriceDto);
////        productRepository.updateProductMyPrice(productId, myprice);
//        verify(productRepository, times(1)).updateProductMyPrice(productId, myprice);
//        //then
//        assertEquals(myprice,product1.getMyprice());
    }

}
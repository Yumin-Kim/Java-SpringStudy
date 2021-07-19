package springcore.fastcamp.fastcamp.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springcore.fastcamp.fastcamp.domin.Product;
import springcore.fastcamp.fastcamp.dto.ProductRequestDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Test
    @DisplayName("테스트 검증")
    void  createProduct_Normal() throws Exception{
        //given
        Long userId = 100L;
        String title = "오리온 꼬북칩 초코츄러스맛 160g";
        String image = "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg";
        String link = "https://search.shopping.naver.com/gate.nhn?id=24161228524";
        int lprice = 2350;

        ProductRequestDto requestDto = new ProductRequestDto(
                title,
                image,
                link,
                lprice
        );

        // when
        Product product = new Product(requestDto, userId);

        // then
        assertNotNull(product.getId());
        assertEquals(userId, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(image, product.getImage());
        assertEquals(link, product.getLink());
        assertEquals(lprice, product.getLprice());
        assertEquals(0, product.getMyprice());
    }
}
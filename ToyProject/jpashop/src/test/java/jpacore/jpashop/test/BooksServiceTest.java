package jpacore.jpashop.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class BooksServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("bookSaveLogicTest")
    @Transactional
    @Rollback(value = false)
    public void bookSaveLogicTest() throws Exception{
        //given
        Books books = new Books();
        books.setName("SpringBook");
        books.setPrice(10000);
        //when
        Books savePoint = bookRepository.save(books);

        //then
        Assertions.assertThat(savePoint.getId()).isEqualTo(books.getId());
    }
}
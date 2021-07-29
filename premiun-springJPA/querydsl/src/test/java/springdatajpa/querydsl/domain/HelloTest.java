package springdatajpa.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HelloTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("querydsl 시작!")
    void start_1() throws Exception{
        //given
        Hello hello = new Hello();
        entityManager.persist(hello);
        //when
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QHello qHello = new QHello("h");
        //then
        Hello hello1 = query.selectFrom(qHello)
                .fetchOne();
        assertEquals(hello1,hello);
        assertEquals(hello1.getId(),hello.getId());
    }


}
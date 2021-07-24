package jpacore.jpashop.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class MemberRepositoryTest {

    @Test
    @DisplayName("쿠폰등록후 모든 회원 조회")
    void membeRepo_1() throws Exception{
        //given

        //when

        //then
    }

    @Test
    @DisplayName("회원 검색 기능 - select문만 체크하고 서비스 계층으로 이동")
    void memberRepo_2() throws Exception{
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void memRepo_3() throws Exception{
        //given

        //when

        //then
    }

}

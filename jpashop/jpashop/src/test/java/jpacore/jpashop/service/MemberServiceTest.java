package jpacore.jpashop.service;

import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberServiceTest {
    @Autowired
    InitDataMethod initDataMethod;

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("캐쉬 충전")
    @Rollback(value = false)
    void service_1() throws Exception{
        //given
        MemberDto memberDto = memberService.chargeStorage(8L, 10000);
        //when
        //then
    }

}

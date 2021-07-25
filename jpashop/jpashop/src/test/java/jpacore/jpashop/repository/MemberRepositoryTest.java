package jpacore.jpashop.repository;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.repository.member.MemberRepository;
import jpacore.jpashop.service.InitDataMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InitDataMethod initDataMethod;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("회원 정보 CRUD")
    void membeRepo_1() throws Exception{
        initDataMethod.createMember();
        em.clear();
        //given
        Member member = memberRepository.findFullMemberById(1L).get();
        System.out.println("member = " + member.getJobs());        //when

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

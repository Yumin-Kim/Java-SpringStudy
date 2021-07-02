package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("회원가입")
//    @Rollback(false)
    public void T_login() throws Exception{
        //given
        Member member = new Member();
        member.setName("Spring");
        Address address = new Address("city", "test", "st");
        member.setAddress(address);
        //when
        Long loginID = memberService.join(member);
        List<Member> members = memberService.findMembers();
//        em.flush();
        //then
        assertEquals(member,memberRepository.findOnd(loginID));
        System.out.println("member.getAddress() = " + member.getAddress().getCity());
        System.out.println("member.getAddress() = " + members.get(0).getAddress().getCitycode());
        Assertions.assertThat(member.getAddress()).isEqualTo(members.get(0).getAddress());
    }


    @Test
    @DisplayName("중복 회원 예외")
    public void T_validateDuplicateMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("Kim");
        Member member2 = new Member();
        member2.setName("Kim");
        //when
        memberService.join(member);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }
        //then
        fail("예외가 발생해야합니다.");
    }
}
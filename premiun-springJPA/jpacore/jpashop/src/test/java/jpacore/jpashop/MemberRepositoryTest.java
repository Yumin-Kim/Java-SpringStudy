//package jpacore.jpashop;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void testMember() throws Exception{
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        //when
//        Long save = memberRepository.save(member);
//        Member member1 = memberRepository.find(save);
//        //then
//        Assertions.assertThat(member1.getId()).isEqualTo(save);
//        System.out.println("member1 = " + member1);
//        System.out.println("member = " + member);
//        Assertions.assertThat(member1).isEqualTo(member1);
//
//    }
//}
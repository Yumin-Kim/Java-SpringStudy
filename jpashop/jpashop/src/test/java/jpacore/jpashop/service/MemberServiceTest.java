package jpacore.jpashop.service;

import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 등록")
    void service_1() throws Exception{
        //given
        MemberForm memberForm = new MemberForm("city", "street", "citycode1");
        memberService.registerMember(memberForm);
        //when
        
        //then
    }

}

package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RemoteServiceTransacTest {

    @Autowired
    RemoteService remoteService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록")
    void register() throws Exception{
        //given
        MemberForm memberForm = makeMemberForm("name", "nickname", "dbals1@naver.com", Arrays.asList("dev", "hello"));
        MemberForm memberForm2 = makeMemberForm("name", "nickname", "dbals0@naver.com", Arrays.asList("dev", "hello"));
        MemberForm memberForm3 = makeMemberForm("name", "nickname1", "dbals1@naver.com", Arrays.asList("dev", "hello"));
        //when
        remoteService.signup(memberForm);
        //then
        assertThrows(RuntimeException.class, () -> remoteService.signup(memberForm2));
        assertThrows(RuntimeException.class, () -> remoteService.signup(memberForm3));
    }



    private MemberForm makeMemberForm(String name, String nickname, String email, List<String> jobs1){
        return new MemberForm("city", "street", "citycode1", name, nickname, "123", email, false, 10, jobs1,0);
    }

    private Member makeMemberInfo(MemberForm memberForm) {
        Address address = Address.of(memberForm.getCity(), memberForm.getStreet(), memberForm.getCitycode());
        List<Job> jobs = memberForm.getJobs().stream()
                .map(Job::createJob).collect(Collectors.toList());
        Member member = Member.createMember(
                memberForm.getName(),
                memberForm.getNickname(),
                memberForm.getPassword(),
                memberForm.getEmail(),
                memberForm.getAge(),
                address,
                jobs
        );
        return member;
    }

}

package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoteServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록")
    void register() throws Exception{
        //given
        RemoteService remoteService = new RemoteService(memberRepository);
        MemberForm memberForm = makeMemberForm("name", "nickname", "dbals1@naver.com", Arrays.asList("dev", "hello"));
        Member member = makeMemberInfo(memberForm);
        MemberForm memberForm2 = makeMemberForm("name", "nickname1", "dbals0@naver.com", Arrays.asList("dev", "hello"));
        Member member2 = makeMemberInfo(memberForm2);

//        when(memberRepository.save(member)).thenReturn(member);
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        when(memberRepository.findByNickname("nickname")).thenReturn(Optional.of(member));
        //then
        MemberDto signup = remoteService.signup(memberForm);

        assertEquals(memberRepository.findByNickname("nickname").get().getEmail(), "dbals1@naver.com");
        RuntimeException nicknameException = assertThrows(RuntimeException.class,()-> remoteService.signup(memberForm));
//        System.out.println("emailException.getMessage() = " + nicknameException.getMessage());
        assertEquals(nicknameException.getMessage(),"nickname는 존재하는 nickname입니다");

    }

    private MemberForm makeMemberForm(String name, String nickname, String email, List<String> jobs1){
        return new MemberForm("city", "street", "citycode1", name, nickname, "123", email, false, 10, jobs1,0);
    }

    private Member makeMemberInfo(MemberForm memberForm) {
        Address address = Address.of(memberForm.getCity(), memberForm.getStreet(), memberForm.getCityCode());
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
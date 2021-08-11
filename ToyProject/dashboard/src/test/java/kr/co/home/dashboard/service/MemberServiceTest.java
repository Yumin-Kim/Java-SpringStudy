package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("findMembersInfo")
    void start_1() throws Exception{
        //given

        List<Member> extracted = extracted(20);
        PageRequest of1 = PageRequest.of(0, 20);
        assertNotNull(memberRepository);
        given(memberRepository.findAll(of1).getContent())
                .willReturn(extracted);
        //when
        List<Res.MemberDto> membersInfo = memberService.findMembersInfo(20, 0);
        //then
        membersInfo.stream()
                .forEach(memberDto->{
                    extracted.stream().forEach(member -> {
                        assertEquals(memberDto.getCity(), member.getName());
                    });
                });
    }

    @Test
    @DisplayName("createMember")
    void start_2() throws Exception{
        //given
        MemberForm memberForm = new MemberForm("name", 12, "dbals", "city", "cityCode", "detailCity");
        given(memberRepository.save(any()))
                .willReturn(memberForm.toEnity());
        //when
        String message = memberService.createMember(memberForm);
        //then
        assertEquals(message, "sucess");
    }

    @Test
    @DisplayName("deleteMember")
    void start_3() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        given(memberRepository.findById(1L))
                .willReturn(Optional.of(member));
        //when
        Res.MemberDto memberDto = memberService.deleteMember(1L);
        //then
        assertEquals(memberDto.getName(), member.getName());
    }

    @Test
    @DisplayName("updateMember")
    void start_4() throws Exception{
        //given
        MemberForm memberForm = new MemberForm(null, 12, null, null, null, null);
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        given(memberRepository.findById(1L))
                .willReturn(Optional.of(member));
        //when
        Res.MemberDto memberDto = memberService.updateMember(1L, memberForm);
        //then
        assertEquals(memberDto.getAge(), 12);
        assertEquals(memberDto.getName(),"name");
    }

    private List<Member> extracted(int i1) {
        List<Member> memberList = new ArrayList<>();
        for (int i = i1; i < 100; i++) {
            Member member = Member.createMember("name"+ i, i, "email"+ i +"@email", Address.createAddress("city"+ i, "citycode"+ i, "DetailCode"+ i));
            memberList.add(member);
        }
        return memberList;
    }

}
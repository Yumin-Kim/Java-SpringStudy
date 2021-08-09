package jpacore.jpashop.service.memberDetail;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.repository.dto.MemberFullDto;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberDetailServiceTest {

    @InjectMocks
    private MemberDetailService memberDetailService;

    @Mock
    private MemberRepository memberRepository;

    private Member member = null;

    @BeforeEach
    void createEntity(){
        Address address = Address.createAddress("city", "street", "cityCode");
        Job dev = Job.createJob("dev");
        Job lop = Job.createJob("lop");
        member = Member.createMember("username", "nickname", "123", "email", 12, address, List.of(dev, lop));
    }

    @Test
    @DisplayName("회원 모든 정보 조회")
    void service_2() throws Exception{
        //given
        MemberFullDto memberFullDto1 = new MemberFullDto(member, any());
        given(memberRepository.findMemberInfoFullJoinV1ById(member.getId()))
                .willReturn(Optional.of(memberFullDto1));
        //when
        MemberFullDto memberFullDto = memberDetailService.selectFullEntity(member.getId());
        //then
        assertNotNull(memberFullDto);
    }

    @Test
    @DisplayName("회원 모든 정보 조회 실패")
    void service_3() throws Exception{
        //given
        given(memberRepository.findMemberInfoFullJoinV1ById(1L))
                .willReturn(Optional.empty());
        //when
        //then
        assertThrows(IllegalStateException.class, ()->memberDetailService.selectFullEntity(1L));
    }

}
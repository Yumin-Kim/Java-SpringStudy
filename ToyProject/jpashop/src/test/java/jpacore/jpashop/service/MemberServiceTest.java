package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.dto.MemberFullDto;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    MemberService memberService;

    @Test
    @DisplayName("캐쉬 충전")
    void service_1() throws Exception{
        //given
        //when
        //then
    }

    @Test
    @DisplayName("회원 모든 정보 조회")
    void service_2() throws Exception{
        //given

        Address address = Address.of("city", "street", "cityCode");
        Job dev = Job.createJob("dev");
        Job lopment = Job.createJob("lopment");

        Member member = Member.createMember("name1", "nickname", "password", "email", 10, address, Arrays.asList(dev, lopment));

        given(memberRepository.findMemberFullCouponById(any()))
                .willReturn(Optional.of(member));
//        given(memberRepository.findMoneyJobById(any())).willReturn(Optional.of(member));
        //when
        MemberFullDto memberFullDto = memberService.selectFullEntity(any());
        //then
        assertNotNull(memberFullDto);
    }

    @Test
    @DisplayName("회원 모든 정보 조회 실패")
    void service_3() throws Exception{
        //given
        Address address = Address.of("city", "street", "cityCode");
        Job dev = Job.createJob("dev");
        Job lopment = Job.createJob("lopment");

        Member member = Member.createMember("name1", "nickname", "password", "email", 10, address, Arrays.asList(dev, lopment));

        given(memberRepository.findMemberFullCouponById(any()))
                .willReturn(Optional.empty());
        //when
        MemberFullDto memberFullDto = memberService.selectFullEntity(any());
        //then
        assertNotNull(memberFullDto);
    }


}

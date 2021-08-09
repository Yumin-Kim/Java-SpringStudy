package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.MoneyStorage;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.dto.MemberFullDto;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    MemberService memberService;

    private Member member = null;

    @BeforeEach
    void createEntity(){
        Address address = Address.createAddress("city", "street", "cityCode");
        Job dev = Job.createJob("dev");
        Job lop = Job.createJob("lop");
        member = Member.createMember("username", "nickname", "123", "email", 12, address, List.of(dev, lop));
    }

    @Test
    @DisplayName("캐쉬 충전")
    void service_1() throws Exception{
        //given
        given(memberRepository.findStorageById(any())).willReturn(Optional.of(member));
        //when
        memberService.chargeStorage(1L, 1000);
        //then
        assertEquals(member.getMoneyStorage().getStoragePoint(),1000);
    }






}

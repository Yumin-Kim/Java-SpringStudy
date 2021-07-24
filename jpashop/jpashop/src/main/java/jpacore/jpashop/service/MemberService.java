package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.member.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void registerMember(MemberForm memberForm) {
        Address address = Address.createAddress(memberForm.getCity(), memberForm.getStreet(), memberForm.getCitycode());
        Member member = Member.createMember(address);
        memberRepository.save(member);
    }

    //고객 정보 수정 로직 작성

}

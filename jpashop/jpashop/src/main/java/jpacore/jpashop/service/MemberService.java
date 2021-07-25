package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.MoneyStorage;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.repository.dto.MemberDto;
import jpacore.jpashop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberDto chargeStorage(Long userId,int point){
        Member member = memberRepository.findMemberStorageById(userId).orElseThrow(() -> new IllegalStateException("존재하지 않는 아이디 입니다"));
        MoneyStorage moneyStorage = member.getMoneyStorage();
        moneyStorage.modifyStoragePoint(moneyStorage.getStoragePoint() + point);
        return new MemberDto(member, moneyStorage);
    }

}

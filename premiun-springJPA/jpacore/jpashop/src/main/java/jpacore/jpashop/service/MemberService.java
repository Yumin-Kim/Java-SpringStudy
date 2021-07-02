package jpacore.jpashop.service;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Transactional
    public Long join(Member member) {
        validationDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validationDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * class 스코프에서 사용하면 전반적으로 선언되어 있는 트랜잭션이 사용되고
     * 원하는 메소드에 애노테이션을 사용하게 되면 해당 메소드만 애노테이션을 다르게 사용할 수 있다.
     * @Transactional readOnly true를 활용하여 최적화 작업을 할 수 있다.
     * 영속성 컨텍스트에서 flush에서 duty체크를 하지 않아 최적화
     * 테이터 변경이 되지 않는다.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Member findOne(Long id) {
        return memberRepository.findOnd(id);
    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = memberRepository.findOnd(id);
        findMember.setName(name);
    }
}

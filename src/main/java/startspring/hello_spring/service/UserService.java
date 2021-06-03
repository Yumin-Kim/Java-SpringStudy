package startspring.hello_spring.service;

import startspring.hello_spring.domain.Member;
import startspring.hello_spring.repository.MemberRepository;
import startspring.hello_spring.repository.MemmoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final MemberRepository memberRepository;

    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * ctrl + alt + v 자동 리턴 값!!
 * 원하는 코드 선택후 alt + m 은 extract Method 실행하여 코드르 간결하고 명확하게 작성한다.
     * ctrl + shift + T 는 기존 class에서 자동으로 test코드 작성
     * @return
     */
    public Long join(Member member){
        validateDuplicateUserName(member);
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }

    private void validateDuplicateUserName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 이름입니다");
        });
    }


}

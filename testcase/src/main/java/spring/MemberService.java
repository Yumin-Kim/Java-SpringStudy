package spring;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);

    void vaildation(Long id);

    void notify(Study study);
}

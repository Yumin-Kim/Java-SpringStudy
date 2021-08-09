package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.repository.dto.MemberFullDto;

import java.util.Optional;

public interface MemberCustomRepository {

    //기본 dto 반환하지 않고 query실행
    Optional<MemberFullDto> findMemberInfoFullJoinV1ById(Long userId);

    //querydsl로 변경 예정 >> dto 반환할 수 있도록
    Optional<Member> findMemberInfoFullJoinV2ById(Long userId);

    //
}

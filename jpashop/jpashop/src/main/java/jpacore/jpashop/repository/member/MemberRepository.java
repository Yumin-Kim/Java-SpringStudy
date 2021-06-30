package jpacore.jpashop.repository.member;

import jpacore.jpashop.dto.UpdateUserInfo;
import jpacore.jpashop.dto.user.DtoUserSearchInfo;
import jpacore.jpashop.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //페이징 지원하는 findAll
    Optional<List<Member>> findAll(int offset, int limit);
    //고객 한명만
    Optional<Long> findOne(Member member);
    //고객 정보 수정
    Optional<Member> updateUserInfo(Long id, UpdateUserInfo updateUserInfo);
    //저장
    void save(Member member);
    //검색 기능 동적 쿼리 사용하여 등급 , 쿠폰 유뮤에 따라 조회
    Optional<List<Member>> searchUserInfo(DtoUserSearchInfo userInfo);
}

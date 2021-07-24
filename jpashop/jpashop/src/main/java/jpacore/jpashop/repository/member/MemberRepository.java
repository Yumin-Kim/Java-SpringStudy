package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.dto.UpdateUserInfo;
import jpacore.jpashop.dto.user.DtoUserSearchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member,Long> {
    //TODO Page 타입으로 반환하는 메소드정의 및 List 타입으로 반환하는 매소드 정의 되어 있다.
    //TODO 테이블 조인 요소 확인
//    @Query("select ")
//    List<Member> findAll(int offset, int limit);
    //TODO 검색 기능 동적 쿼리 사용하여 등급 , 쿠폰 유뮤에 따라 조회
    // TODO MemberReop : 검색 기능을 위한 동적 쿼리 작성 요구 // QueryDSL을 배우지 않아 현재로는 Projections사용하는 방향으로
//    List<Member> searchUserInfo(DtoUserSearchInfo userInfo);
}

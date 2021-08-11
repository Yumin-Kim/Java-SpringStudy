package jpacore.jpashop.repository.member;

import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.UpdateUserInfo;
import jpacore.jpashop.dto.user.DtoUserSearchInfo;
import jpacore.jpashop.repository.dto.ItemDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberCustomRepository {

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    //Todo 돈과 직업은 베이스 join으로 발생하게 된다.
    @Query("select m from Member m join fetch m.moneyStorage where m.id = :id")
    Optional<Member> findStorageById(@Param("id") Long userId);

    @Query("select m from Member m join fetch m.moneyStorage ms join fetch m.jobs js where m.id = :id")
    Optional<Member> findMoneyAndJobById(@Param("id") Long userId);

    @Query("select m from Member m join fetch m.moneyStorage ms join fetch m.jobs js join fetch m.couponMembers cm join fetch cm.coupon where m.id = :id")
    Optional<Member> findMemberJobStorageMoneyById(@Param("id") Long userId);

    @Query("select m from Member m " +
            "join fetch m.moneyStorage ms " +
            "join fetch m.jobs js " +
            "join fetch m.couponMembers cm " +
            "join fetch m.favoriteItem fi " +
            "join fetch cm.coupon where m.id = :id")
    Optional<Member> findMemberJobStorageMoneyListById(@Param("id") Long userId);

    
    @Query("select distinct m from Member m join fetch m.moneyStorage ms join fetch m.jobs j join m.favoriteItem fi where m.id = :id")
    Optional<Member> findFavoriteById(@Param("id") Long userId);


    @Query("select m from Member m join fetch m.moneyStorage ms join fetch m.jobs j join m.favoriteItem fi where m.id = :id")
    List<Member> findFavoriteListById(@Param("id") Long userId);

    @EntityGraph(attributePaths = {"moneyStorage","couponMembers","favoriteItem","jobs"})
    @Query("select m from Member m where m.id = :id")
    Optional<Member> findJFMCById(@Param("id") Long userId);
    ///////

    //우사 제품만
    @Query(value = "select m from Member m join fetch m.favoriteItem fi  where m.id = :id")
    Optional<Member> findFavoriteItemsById(@Param("id") Long userId);

    @EntityGraph(attributePaths = {"jobs"})
    Optional<Member> findMemberJobById(Long userId);

    /**
     * link 절 테스트 V1 조회 항목이 부분적으로 동일한지 확인
     *
     * @param getJobsName
     * @return
     */
    @EntityGraph(attributePaths = {"jobs"})
    @Query("select m from Member m join fetch m.jobs j where j.name like concat(:jobs,'%')")
    List<Member> findMemberJobByNameLike(@Param("jobs") String getJobsName);

    @Query("select m from Member m join fetch m.jobs j where j.name in :jobs")
    List<Member> findMemberJobByNameIn(@Param("jobs") List<String> jobsName);

    @Query(value = "select m from Member m join fetch m.favoriteItem fi where m.id in :ids")
    List<Member> findJobsFavoriteByIds(@Param("ids") List<Long> userIds);
    //TODO Page 타입으로 반환하는 메소드정의 및 List 타입으로 반환하는 매소드 정의 되어 있다.
    //TODO 테이블 조인 요소 확인
//    @Query("select ")
//    List<Member> findAll(int offset, int limit);
    //TODO 검색 기능 동적 쿼리 사용하여 등급 , 쿠폰 유뮤에 따라 조회
    // TODO MemberReop : 검색 기능을 위한 동적 쿼리 작성 요구 // QueryDSL을 배우지 않아 현재로는 Projections사용하는 방향으로
//    List<Member> searchUserInfo(DtoUserSearchInfo userInfo);
}

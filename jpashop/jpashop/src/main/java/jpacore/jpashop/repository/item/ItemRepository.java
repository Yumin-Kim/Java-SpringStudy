package jpacore.jpashop.repository.item;

import jpacore.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//query 요청 응답 관련만 로직 작성
//여기서 모든것을 책임 지려는 클래스 개발하지 않기
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 상품 등록
    Optional<Item> save (Item item);
    // 상품 하나만 조회
    // 모든 상품 조회
    // 상품 정보 하나만 수정
    // 비즈니스 요구에 따른 모든 정보 수정
}

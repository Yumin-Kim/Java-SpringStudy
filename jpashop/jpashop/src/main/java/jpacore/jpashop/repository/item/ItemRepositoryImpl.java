package jpacore.jpashop.repository.item;

import jpacore.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Predicate;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl {
    private final ItemRepository itemRepository;

    // 상품 등록
    public boolean save(Item item){
        Item save = itemRepository.save(item).get();

    }
    // 상품 하나만 조회
    // 모든 상품 조회
    // 상품 정보 하나만 수정
    // 비즈니스 요구에 따른 모든 정보 수정

}

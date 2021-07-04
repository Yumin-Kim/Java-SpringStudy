package jpacore.jpashop.repository.item;

import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.item.dto.UpdateItemDto;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    //item 정보 저장
    void save(Item item);

    //item 정보 수정
    Item updateItem(UpdateItemDto updateItemDto);

    // item 정보 여러 정보 한번에 수정
    List updateItems(List<UpdateItemDto> updateItemDtos);

    //item 정보 찾기 1
    Item findItem(Long id);

    //item 정보 모두 찾기ㅣ
    List<Item> findAllItems();

    // item 정보 페이지 기능 추가하여 조회
    List<Item> findAllItemsPaging(int offset, int limit);

    // item 종류에 따라 페이징
    List<Item> findAllClassificationItems(int offset, int limit);

}

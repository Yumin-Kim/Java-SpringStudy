package jpacore.jpashop.service;

import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.ItemForm;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 등록")
    void start_1() throws Exception{
        //given
        ItemForm book = ItemForm.builder()
                .name("item1")
                .itemType("B")
                .company("company1")
                .stockQutity(100)
                .artist("artist")
                .etc("etc1")
                .build();

        ItemForm movie = ItemForm.builder()
                .name("item2")
                .itemType("M")
                .company("company2")
                .stockQutity(100)
                .director("director1")
                .actor("actor1")
                .build();

        ItemForm album = ItemForm.builder()
                .name("item2")
                .itemType("A")
                .company("company2")
                .stockQutity(100)
                .isbn("isbn1")
                .author("author1")
                .build();

        //when
        ItemDto itemDto = itemService.create(book);
        ItemDto itemDto1 = itemService.create(movie);
        ItemDto itemDto2 = itemService.create(album);
        System.out.println("itemDto2.toString() = " + itemDto2.toString());
        System.out.println("itemDto2.toString() = " + itemDto1.toString());
        System.out.println("itemDto2.toString() = " + itemDto.toString());

        //then
        itemRepository.findByTypeBAll();
    }

}
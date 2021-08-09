package jpacore.jpashop.repository.item.old;

import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.item.dto.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class Old_ItemRepositoryImpl implements Old_ItemRepository {

    private final EntityManager em;

    @Override
    public void save(Item item) {
        em.persist(item);
    }

    //서비스 계층에서 Request와 기존 Item 정보 비교 >> UpdateDTO생성
    // 엔티티에서 수정 // setter사용하지 않는다. 수정 로직은 reflection을 활용하여
    // 수정하는 방식으로 진행
    @Override
    public Item updateItem(UpdateItemDto updateItemDto) {
        Method[] declaredMethods = updateItemDto.getClass().getDeclaredMethods();
        Arrays.stream(declaredMethods)
                .forEach(declaredMethod->{
                    declaredMethod.getName().equals("get");
                });
        return null;
    }

    @Override
    public List updateItems(List<UpdateItemDto> updateItemDtos) {
        return null;
    }

    @Override
    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAllItems() {
        return null;
    }

    @Override
    public List<Item> findAllItemsPaging(int offset, int limit) {
        return null;
    }

    @Override
    public List<Item> findAllClassificationItems(int offset, int limit) {
        return null;
    }
}

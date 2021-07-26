package jpacore.jpashop.repository.item;

import jpacore.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    //TODO 쿼리
    @Query("select b from Book b")
    List<Item> findByTypeBAll();

    @Query("select i from Item i where i.artist = :artist and i.etc = :etc and i.name = :name")
    Item findByArtistAndEtc(@Param("artist") String artist1, @Param("etc") String etc1, @Param("name") String book1);

    @Query("select i from Item i where i.id in :ids")
    List<Item> findItemsByIds(@Param("ids") List<Long> itemIdList);
}

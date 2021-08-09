package jpacore.jpashop.repository;

import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.item.old.Old_ItemRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    private Old_ItemRepositoryImpl itemRepository1;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("아이템 정보 저장")
    @Transactional
    void createItem() throws Exception{
        //given
        Book book = Book.createBook("BookSpring", 1000, "A", "kim", "etc1");
        Album album = Album.createAlbum("AlbumSpring", 1001, "A_Album", "author1", "isbn1");
        Movie movie = Movie.createMovie("MovieSpring", 10002, "B_Movie", "direcotr", "actor1");
        //when
        itemRepository1.save(book);
        itemRepository1.save(album);
        itemRepository1.save(movie);
        //then
    }

    @Test
    @DisplayName("아이템별 정보 조회")
    @Transactional
    @Rollback(value = false)
    void findItemInfo() throws Exception {
        //given
        //when
        Item item = itemRepository1.findItem(1L);
        //then
        assertThat(item.getName()).isEqualTo("BookSpring");
    }
    
    @Test
    @DisplayName("")
    void find() throws Exception{
        //given
        itemRepository.findByTypeBAll();
        //when

        //then
    }

}
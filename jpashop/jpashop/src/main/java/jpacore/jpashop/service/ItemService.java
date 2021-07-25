package jpacore.jpashop.service;

import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import jpacore.jpashop.dto.ItemForm;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ItemDto create(ItemForm itemForm) {
        switch (itemForm.getItemType()) {
            case "B":
                Book saveItem = getItemDto(itemForm);
                return new ItemDto(saveItem, saveItem.getArtist(), saveItem.getEtc());
            case "A":
                Album album = getItemDto(itemForm);
                return new ItemDto(album, album.getAuthor(), album.getIsbn());
            case "M":
                Movie movie = getItemDto(itemForm);
                return new ItemDto(movie, movie.getActor(), movie.getDirector());
            default:
                return null;
        }
    }

    private <T extends Item> T getItemDto(ItemForm itemForm) {
        T item = itemForm.toEntityBook(itemForm.getItemType());
        T saveItem = itemRepository.save(item);
        return saveItem;
    }

}

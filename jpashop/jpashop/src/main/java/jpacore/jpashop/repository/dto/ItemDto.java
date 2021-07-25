package jpacore.jpashop.repository.dto;

import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class ItemDto {

    private Long id;
    private String name;
    private int stockQutity;
    private String company;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String author;
    private String isbn;
    private String director;
    private String actor;
    private String artist;
    private String etc;

    public ItemDto(Item saveItem) {
        //TODO item이 슈퍼클래스로 구성되어 있기 때문에 null인값이 존재 어떻게 get,set할 것인지??
        id = saveItem.getId();
        name = saveItem.getName();
        company = saveItem.getCompany();
        stockQutity = saveItem.getPrice();
        createDate = saveItem.getCreateDate();
        lastModifiedDate = saveItem.getLastModifiedDate();
    }

    public ItemDto(Book saveItem, String artist, String etc) {
        id = saveItem.getId();
        name = saveItem.getName();
        company = saveItem.getCompany();
        stockQutity = saveItem.getPrice();
        createDate = saveItem.getCreateDate();
        lastModifiedDate = saveItem.getLastModifiedDate();
        this.etc = etc;
        this.artist = artist;
    }

    public ItemDto(Album album, String author, String isbn) {
        id = album.getId();
        company = album.getCompany();
        name = album.getName();
        stockQutity = album.getPrice();
        createDate = album.getCreateDate();
        lastModifiedDate = album.getLastModifiedDate();
        this.author = author;
        this.isbn = isbn;
    }


    public ItemDto(Movie movie, String actor, String director) {
        id = movie.getId();
        name = movie.getName();
        company = movie.getCompany();
        stockQutity = movie.getPrice();
        createDate = movie.getCreateDate();
        lastModifiedDate = movie.getLastModifiedDate();
        this.actor = actor;
        this.director = director;
    }

}

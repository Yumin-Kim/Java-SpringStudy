package jpacore.jpashop.dto;

import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class ItemForm {

    @NotEmpty(message = "item 카테고리를 입력해주세요")
    private String itemType;
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

    @Builder
    public ItemForm(String itemType, String name, int stockQutity, String company, LocalDateTime createDate, LocalDateTime lastModifiedDate, String author, String isbn, String director, String actor, String artist, String etc) {
        this.itemType = itemType;
        this.name = name;
        this.stockQutity = stockQutity;
        this.company = company;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
        this.artist = artist;
        this.etc = etc;
    }

    public<T> T toEntityBook(String itemType){

        switch (itemType){
            case "B":
                return (T) Book.createBook(name, stockQutity, company, artist, etc);
            case "M":
                return (T) Movie.createMovie(name, stockQutity, company, director, actor);
            case "A":
                return (T) Album.createAlbum(name, stockQutity, company,author,isbn);
            default:
                throw new RuntimeException("item Type이 잘못되었습니다");
        }

    }



}

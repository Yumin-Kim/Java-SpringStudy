package jpacore.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("A")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {
    private String author;
    private String isbn;

    public Album(String name, int stockPrice, String company, String author, String isbn) {
        super(name, stockPrice, company);
        this.author = author;
        this.isbn = isbn;
    }

    public static Album createAlbum(String name, int stockPrice, String company, String author, String isbn){
        return new Album(name, stockPrice, company, author, isbn);
    }

}

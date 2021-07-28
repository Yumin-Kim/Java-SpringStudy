package jpacore.jpashop.domain.item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("B")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    private String artist;
    private String etc;

    Book(String name, int stockPrice, String company, String artist, String etc) {
        super(name, stockPrice, company);
        this.artist = artist;
        this.etc = etc;
    }

    public static Book createBook(String name, int stockPrice, String company, String artist, String etc){
        return new Book(name, stockPrice, company, artist, etc);
    }


}

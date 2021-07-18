package jpacore.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("M")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {
    private String director;
    private String actor;

    public Movie(String name, int stockPrice, String company, String director, String actor) {
        super(name, stockPrice, company);
        this.director = director;
        this.actor = actor;
    }

    public static Movie createMovie(String name, int stockPrice, String company, String director, String actor){
        return new Movie(name, stockPrice , company ,director,actor );
    }


}

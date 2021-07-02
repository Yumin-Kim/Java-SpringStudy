package jpacore.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String artist;
    private String etc;
}
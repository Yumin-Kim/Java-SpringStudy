package jpacore.jpashop.test;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "test_book")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Books {

    @Id
    @GeneratedValue
    @Column(name = "test_book_id")
    private Long id;

    public Books() {
    }

    private String name;
    private int price;

}

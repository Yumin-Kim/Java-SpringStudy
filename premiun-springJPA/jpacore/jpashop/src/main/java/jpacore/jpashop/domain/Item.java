package jpacore.jpashop.domain;

import jpacore.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class  Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stackQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int stock){
        this.stackQuantity += stock;
    }

    public void removeStock(int queutity){
        int restStock = this.stackQuantity - queutity;
        if (restStock < 0) {
            throw new NotEnoughStockException("Need more stock");
        }
        this.stackQuantity = restStock;
    }

}

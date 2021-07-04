package jpacore.jpashop.repository.item.dto;

import jpacore.jpashop.domain.Category;
import jpacore.jpashop.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class UpdateItemDto {
    private String name;
    private int stockPrice;
    private String company;
//    private List<Category> categories = new ArrayList<>();
}

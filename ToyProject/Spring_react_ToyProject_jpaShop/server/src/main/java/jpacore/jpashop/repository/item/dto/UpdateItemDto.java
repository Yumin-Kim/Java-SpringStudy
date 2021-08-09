package jpacore.jpashop.repository.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemDto {
    private String name;
    private int stockPrice;
    private String company;
//    private List<Category> categories = new ArrayList<>();
}

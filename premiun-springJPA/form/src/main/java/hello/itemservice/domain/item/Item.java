package hello.itemservice.domain.item;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Item {

    private Long id;
    @NotBlank(message = "공백입니다.")
    private String itemName;
    @Max(value = 1000 , message = "최대금액은 1000원 입니다.")
    @Min(value = 1 , message = "최소 금액은 1원입니다")
    private Integer price;
    @NotNull(message = "공백 입니다.")
    @Min(value = 0,message = "최소는 1입니다.")
    private Integer quantity;

    private Boolean open;
    private List<String> regions;
    private ItemType itemType;
    private String deliveryCode;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

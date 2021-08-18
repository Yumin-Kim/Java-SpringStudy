package hello.itemservice.domain.item;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;

}

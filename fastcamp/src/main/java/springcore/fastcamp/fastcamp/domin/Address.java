package springcore.fastcamp.fastcamp.domin;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String cityCode;
    private String city;
}

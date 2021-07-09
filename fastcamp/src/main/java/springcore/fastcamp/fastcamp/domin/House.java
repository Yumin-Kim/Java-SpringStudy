package springcore.fastcamp.fastcamp.domin;

import lombok.*;

import javax.persistence.*;
//Setter 사용하지 않고 진행해보기
@Entity
@Getter
@Table(name = "Houses")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class House {

    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private Long id;

    private String name;

    private Address address;

    @Builder
    public House(String name, Address address, int houseSize) {
        this.name = name;
        this.address = address;
        this.houseSize = houseSize;
    }

    private int houseSize;

}

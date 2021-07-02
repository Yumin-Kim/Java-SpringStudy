package jpacore.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Embedded
    private Address address;

    private String name ;
    @OneToMany(mappedBy = "member")
    private List<Order> orders  = new ArrayList<>();



}

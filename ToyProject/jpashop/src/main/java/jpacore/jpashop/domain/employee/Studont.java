package jpacore.jpashop.domain.employee;

import jpacore.jpashop.domain.Address;

import javax.persistence.*;

@Entity
public class Studont extends Person {
    private String stuCode;
    @Embedded
    private Address address;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "student_id")),
            @AttributeOverride(name = "count", column = @Column(name = "count_id"))
    })
    private UserData userData;
}

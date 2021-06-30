package jpacore.jpashop.domain.employee;

import jpacore.jpashop.domain.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Teacher extends Person {
    private String teacherCode;
    @Embedded
    private Address address;
}

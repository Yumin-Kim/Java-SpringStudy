package jpacore.jpashop.domain.employee;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id @GeneratedValue
    private Long id;
    private String name;

}

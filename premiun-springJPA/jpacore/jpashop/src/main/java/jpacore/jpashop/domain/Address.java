package jpacore.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    String city;
    String street;
    String citycode;

    protected Address(   ){

    }

    public Address(String city, String street, String citycode) {
        this.city = city;
        this.street = street;
        this.citycode = citycode;
    }
}




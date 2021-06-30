package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String street;
    private String citycode;

    Address(String city, String street, String citycode) {
        this.city = city;
        this.street = street;
        this.citycode = citycode;
    }

    public static Address createAddress(String city , String street , String citycode){
        return new Address(city, street, citycode);
    }

    public void updateCity(String city) {
        this.city = city;
    }

    public void updateCityCode(String cityCode) {
        this.city = cityCode;
    }

    public void updateStreet(String street) {
        this.street = street;
    }
}

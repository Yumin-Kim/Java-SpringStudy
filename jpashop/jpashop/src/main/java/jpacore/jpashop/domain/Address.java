package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String citycode;

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

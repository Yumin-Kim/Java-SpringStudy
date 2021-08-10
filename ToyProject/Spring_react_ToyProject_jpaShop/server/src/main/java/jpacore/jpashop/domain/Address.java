package jpacore.jpashop.domain;

import lombok.*;

import javax.persistence.Access;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class    Address {
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

    public Address updateAddress(Member member, String city, String citycode, String street) {
        if (city != null){
            member.getAddress().city = city;
        }
        if (citycode != null) {
            member.getAddress().citycode = citycode;
        }
        if (street != null) {
            member.getAddress().street = street;
        }
        return member.getAddress();
    }
}

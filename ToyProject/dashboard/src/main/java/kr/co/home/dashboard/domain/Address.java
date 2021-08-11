package kr.co.home.dashboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String cityCode;
    private String detailCity;

    @Builder
    protected Address(String city, String cityCode, String detailCity) {
        this.city = city;
        this.cityCode = cityCode;
        this.detailCity = detailCity;
    }

    public static Address createAddress(String city, String cityCode, String detailCity) {
        return Address.builder()
                .city(city)
                .cityCode(cityCode)
                .detailCity(detailCity)
                .build();
    }

    public void updateCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void updateDetailCity(String detailCity) {
        this.detailCity = detailCity;
    }

    public void updateCity(String city) {
        this.city = city;
    }
}

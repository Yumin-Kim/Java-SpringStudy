package data.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "city")
public class MemberAddressDto {
    private int cityCode;
    private String city;

    public MemberAddressDto(int cityCode, String city) {
        this.cityCode = cityCode;
        this.city = city;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

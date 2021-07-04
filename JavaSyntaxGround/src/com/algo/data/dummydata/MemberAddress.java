package com.algo.data.dummydata;

import lombok.Data;

@Data
public class MemberAddress {
    private String cityCode;
    private String city;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MemberAddress(String cityCode, String city) {
        this.cityCode = cityCode;
        this.city = city;
    }
}

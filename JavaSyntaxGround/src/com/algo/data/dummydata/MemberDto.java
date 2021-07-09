package com.algo.data.dummydata;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
public class MemberDto {
    private int id;
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

    public MemberDto(int id,String cityCode, String city) {
        this.id = id;
        this.cityCode = cityCode;
        this.city = city;
    }
}

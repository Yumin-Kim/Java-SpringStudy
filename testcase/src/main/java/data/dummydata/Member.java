package data.dummydata;

import lombok.Data;

@Data
public class Member {
    private int id;
    private String name;
    private String phoneNumber;
    private String cityCode;
    private String city;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    public Member(int id,String name, String phoneNumber, String cityCode, String city, int count) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cityCode = cityCode;
        this.city = city;
        this.count = count;
    }
}

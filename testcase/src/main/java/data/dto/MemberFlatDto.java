package data.dto;

public class MemberFlatDto {
    private String name;
    private String phoneNumber;

    private int cityCode;
    private String city;

    public MemberFlatDto(String name, String phoneNumber, int cityCode, String city) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cityCode = cityCode;
        this.city = city;
    }

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

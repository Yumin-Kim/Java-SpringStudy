package com.algo.data.dto;

import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(of = "name")
public class MemberQueryDto {
    private String name;
    private String phoneNumber;
    private List<MemberAddressDto> memberAddressDtos;

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

    public List<MemberAddressDto> getMemberAddressDtos() {
        return memberAddressDtos;
    }

    public void setMemberAddressDtos(List<MemberAddressDto> memberAddressDtos) {
        this.memberAddressDtos = memberAddressDtos;
    }

    public MemberQueryDto(String name, String phoneNumber, List<MemberAddressDto> memberAddressDtos) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.memberAddressDtos = memberAddressDtos;
    }

    public MemberQueryDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

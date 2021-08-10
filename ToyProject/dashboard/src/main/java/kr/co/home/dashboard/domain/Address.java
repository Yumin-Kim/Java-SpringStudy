package kr.co.home.dashboard.domain;

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

}

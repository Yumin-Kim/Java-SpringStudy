package kr.co.multiadmin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class General {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String name;

}

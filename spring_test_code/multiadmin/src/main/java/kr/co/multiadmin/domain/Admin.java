package kr.co.multiadmin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String name;

    @Builder
    protected Admin(String username, String name) {
        this.username = username;
        this.name = name;
    }
}

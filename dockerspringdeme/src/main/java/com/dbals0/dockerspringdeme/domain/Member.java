package com.dbals0.dockerspringdeme.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    protected Member(String name) {
        this.name = name;
    }

    public static Member createMember(String name) {
        return Member.builder()
                .name(name)
                .build();
    }

    public void modifiedTeam(Team t) {
        this.team = t;
    }
}

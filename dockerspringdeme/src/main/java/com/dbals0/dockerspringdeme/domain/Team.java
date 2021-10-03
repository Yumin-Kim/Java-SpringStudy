package com.dbals0.dockerspringdeme.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    @Builder
    protected Team(String name) {
        this.name = name;
    }

    public static Team createTeam(String team1) {
        return Team.builder()
                .name(team1)
                .build();
    }
}

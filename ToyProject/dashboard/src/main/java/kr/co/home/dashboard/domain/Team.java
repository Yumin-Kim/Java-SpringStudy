package kr.co.home.dashboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    private int MaxTeamMember;

    @OneToMany(mappedBy = "team")
    private Member member;

    protected Team(String name, int maxTeamMember) {
        this.name = name;
        MaxTeamMember = maxTeamMember;
    }

    public static Team createTeam(String name, Integer maxTeamMember) {
        return new Team(name, maxTeamMember);
    }


}

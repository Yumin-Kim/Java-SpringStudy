package kr.co.home.dashboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberTeam {
    @Id
    @GeneratedValue
    @Column(name = "member_team_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status stats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    protected MemberTeam(Member member, Team team) {
        this.stats = Status.CREATED;
        this.member = member;
        this.team = team;
    }

    public static MemberTeam createRelation(Member member, Team team) {
        return new MemberTeam(member, team);
    }

}

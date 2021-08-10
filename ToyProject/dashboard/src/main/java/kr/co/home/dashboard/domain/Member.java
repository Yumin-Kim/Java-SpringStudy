package kr.co.home.dashboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Team> teams = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Dashboard> dashboards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

}

package kr.co.home.dashboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "dashboard")
public class Dashboard {

    @Id
    @GeneratedValue
    @Column(name = "dashboard_id")
    private Long id;

    private String title;

    private String subTitle;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "dashboard")
    private List<Comment> comments = new ArrayList<>();



}

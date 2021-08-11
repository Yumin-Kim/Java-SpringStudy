package kr.co.home.dashboard.domain;

import kr.co.home.dashboard.dto.MemberForm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Where(clause = "is_deleted = 0")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    private String email;

    @ColumnDefault("0")
    private Boolean isDeleted;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<MemberTeam> memberTeams = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Dashboard> dashboards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    protected Member(String name, int age, String email, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.isDeleted = false;
    }

    public static Member createMember(String name, int age, String email, Address address) {
        return Member.builder()
                .address(address)
                .age(age)
                .email(email)
                .name(name)
                .build();
    }

    public void updateDeleted() {
        this.isDeleted = true;
    }

    public void updateMemberInfo(MemberForm memberForm) {
        if (memberForm.getAge() >0) {
            this.age = memberForm.getAge();
        }
        if (hasText(memberForm.getCity())) {
            this.address.updateCity(memberForm.getCity());
        }
        if (hasText(memberForm.getDetailCity())) {
            this.address.updateDetailCity(memberForm.getDetailCity());
        }
        if (hasText(memberForm.getEmail())) {
            this.email = memberForm.getEmail();
        }
        if (hasText(memberForm.getName())) {
            this.name = memberForm.getName();
        }
        if (hasText(memberForm.getCityCode())) {
            this.address.updateCityCode(memberForm.getCityCode());
        }
    }

}

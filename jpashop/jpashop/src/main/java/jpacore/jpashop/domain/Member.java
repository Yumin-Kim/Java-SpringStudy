package jpacore.jpashop.domain;

import jpacore.jpashop.domain.enumtype.MemberStatus;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.UpdateUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
//@Table(
//        name = "members",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"nickname","email"})
//        }
//)
//@Builder
//@Where(clause = "privacy = false")
public class Member extends BaseEntity {
    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String nickname;
    private String password;
    private String email;

    @ColumnDefault("0")
    private boolean privacy;
    //TODO 나이는 birth 입력하여 연산하는 방식으로 변경 예정
    private int age;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "money_storage_id")
    private MoneyStorage moneyStorage;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<CouponMember> couponMembers = new ArrayList<>();

    /////////////////////
    /////////////////////컬렉션 엔티티
    @ElementCollection
    @CollectionTable(
            name = "favorite_item",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Column(name = "favorite_item")
    private Set<Item> favoriteItem = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "job",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private List<Job> jobs = new ArrayList<>();

    /////////////////////

    Member(Address address, MemberStatus memberStatus) {
        this.address = address;
        this.memberStatus = memberStatus;
    }

    public Member(String name, String nickname, String password, String email, boolean privacy, int age, Address address, MemberStatus memberStatus, List<Job> jobs) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.privacy = privacy;
        this.age = age;
        this.address = address;
        this.memberStatus = memberStatus;
        this.jobs = jobs;
        MoneyStorage moneyStorage = new MoneyStorage();
        moneyStorage.modifyStoragePoint(0);
        this.moneyStorage = moneyStorage;
    }

    public static Member createMember(String name, String nickname, String password, String email, int age, Address userAddress, List<Job> jobs) {
        return new Member(name, nickname, password, email, false, age, userAddress, MemberStatus.BASIC, jobs);
    }

    ///////////////Test///////////////
    public void updateUserMemory(UpdateUserInfo updateUserInfo) {
        Arrays.stream(updateUserInfo.getClass().getDeclaredFields()).findFirst().stream().forEach((v) -> {
            Arrays.stream(this.getAddress().getClass().getDeclaredFields()).forEach((inner) -> {
                if (inner.getName().equals(v.getName())) {
                }
            });
        });
    }

    public void updateJob(Job... job) {
        jobs.addAll(Arrays.asList(job));
    }

    public void updateCity(String updateCity) {
        this.getAddress().updateCity(updateCity);
    }

    public void updateUserInfo(UpdateUserInfo updateUserInfo) {
        Arrays.stream(updateUserInfo.getClass().getDeclaredFields())
                .forEach((updatefield) -> {
                    updatefield.setAccessible(true);
                    try {
                        if (updatefield.get(updateUserInfo) != null) {
                            switch (updatefield.getName()) {
                                case "city":
                                    this.address.updateCity(updateUserInfo.getCity());
                                    break;
                                case "citycode":
                                    this.address.updateCityCode(updateUserInfo.getCityCode());
                                    break;
                                case "street":
                                    this.address.updateStreet(updateUserInfo.getStreet());
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}

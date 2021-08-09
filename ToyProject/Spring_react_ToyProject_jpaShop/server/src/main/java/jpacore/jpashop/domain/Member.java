package jpacore.jpashop.domain;

import jpacore.jpashop.domain.enumtype.MemberStatus;
import jpacore.jpashop.dto.MemberForm;
import jpacore.jpashop.dto.UpdateUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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
@Where(clause = "is_deleted = 0")
// TODO where 애노테이션을 통해서 삭제된 항목은 가지고 오지 않게 한다. 실제 데이터 베이스 등록 되는 컬럼 값을 적어줘야하며 JPA 부가적으로 컴파일 하는 과정이 존재하지 않는것 같다
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

    @ColumnDefault("0")
    private boolean isDeleted;

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
    private Set<CouponMember> couponMembers = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "favorite_item",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private Set<FavoriteItem> favoriteItem = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "job",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private List<Job> jobs = new ArrayList<>();

    protected Member(String name, String nickname, String password, String email, boolean privacy, int age, Address address, MemberStatus memberStatus, List<Job> jobs) {
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

    public void updateJob(Job... job) {
        jobs.addAll(Arrays.asList(job));
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

    public void modifyMemberInfo(Member member, MemberForm memberForm, Address address) {
        if (memberForm.getPassword() != null) {
            member.password = memberForm.getPassword();
        }
        if (memberForm.getJobs() != null) {
            if (memberForm.getJobs().size() > 0)
                member.jobs = memberForm.getJobs().stream()
                        .map(Job::createJob).collect(Collectors.toList());
        }
        if (memberForm.getNickname() != null) {
            member.nickname = memberForm.getNickname();
        }
        if (memberForm.getAge() != null) {
            member.age = memberForm.getAge();
        }
        if (memberForm.getEmail() != null) {
            member.email = memberForm.getEmail();
        }
        if (!memberForm.getPrivacy()) {
            member.privacy = memberForm.getPrivacy();
        }
        if (memberForm.getCityCode() != null || memberForm.getStreet() != null || memberForm.getCity() != null) {
            member.address.updateAddress(member, memberForm.getCity(), memberForm.getCityCode(), memberForm.getStreet());
        }

    }

    public void updateIsDeleted(boolean valid) {
        isDeleted = valid;
    }

    public void updateFavoriteItemList(List<FavoriteItem> createFavoriteItems) {
        favoriteItem.addAll(createFavoriteItems);
    }
}

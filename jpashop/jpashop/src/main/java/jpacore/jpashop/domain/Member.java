package jpacore.jpashop.domain;

import jpacore.jpashop.dto.UpdateUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Member {
    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long id;
    @Embedded//
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<CouponMember> couponMembers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;


    @ElementCollection
    @CollectionTable(
            name = "FAVORITE_FOODS",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoritefoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "address_hisoty",
            joinColumns = @JoinColumn(name = "member_id")
    )
    private List<Address> history = new ArrayList<>();

    Member(Address address, MemberStatus memberStatus) {
        this.address = address;
        this.memberStatus = memberStatus;
    }

    public static Member createMember( Address userAddress){
        return new Member(userAddress, MemberStatus.BASIC);
    }

    ///////////////Test///////////////
    public void updateUserMemory(UpdateUserInfo updateUserInfo) {
        Arrays.stream(updateUserInfo.getClass().getDeclaredFields()).findFirst().stream().forEach((v)->{
            Arrays.stream(this.getAddress().getClass().getDeclaredFields()).forEach((inner)->{
                if(inner.getName().equals(v.getName())){
                }
            });
        });
    }

    public void updateCity(String updateCity){
        this.getAddress().updateCity(updateCity);
    }

    public void updateUserInfo(UpdateUserInfo updateUserInfo) {
        Arrays.stream(updateUserInfo.getClass().getDeclaredFields())
                .forEach((updatefield)->{
                    updatefield.setAccessible(true);
                    try {
                        if(updatefield.get(updateUserInfo) != null ){
                            switch (updatefield.getName()){
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

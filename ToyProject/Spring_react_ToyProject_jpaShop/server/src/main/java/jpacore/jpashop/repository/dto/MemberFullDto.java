package jpacore.jpashop.repository.dto;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
public class MemberFullDto {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private boolean privacy;
    private int age;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Address address;
    private int storage;
    private List<Job> jobs;
    private List<ItemDto> favoriteItems;
    private List<CouponDto> myCoupons;

    public MemberFullDto(Member member) {
        this.id = member.getId();
        this.username = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.privacy = member.isPrivacy();
        this.age = member.getAge();
        this.createDate = member.getCreateDate();
        this.lastModifiedDate = member.getLastModifiedDate();
        this.address = member.getAddress();
        this.storage = member.getMoneyStorage().getStoragePoint();
        this.jobs = member.getJobs();
    }

    public MemberFullDto(Member member, List<Item> memberSelectItems) {
        this.id = member.getId();
        this.username = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.privacy = member.isPrivacy();
        this.age = member.getAge();
        this.createDate = member.getCreateDate();
        this.lastModifiedDate = member.getLastModifiedDate();
        this.address = member.getAddress();
        this.storage = member.getMoneyStorage().getStoragePoint();
        this.jobs = member.getJobs();
        if (member.getCouponMembers().size() > 0) {
            List<CouponDto> couponDtos = member.getCouponMembers().stream()
                    .map(couponMember -> new CouponDto(couponMember.getCoupon()))
                    .collect(toList());
            this.myCoupons = couponDtos;
        }
        if (memberSelectItems != null) {
            if (memberSelectItems.size() > 0) {
                List<ItemDto> itemDtos = memberSelectItems.stream()
                        .map(ItemDto::new)
                        .collect(toList());

                this.favoriteItems = itemDtos;
            }
        }
    }
}

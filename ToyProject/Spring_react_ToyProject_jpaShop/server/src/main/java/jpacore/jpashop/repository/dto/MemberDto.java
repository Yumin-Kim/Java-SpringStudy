package jpacore.jpashop.repository.dto;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Job;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.MoneyStorage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class MemberDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private boolean privacy;
    private int age;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private Address address;
    private int storage;
    private List<Job> jobs;

    public MemberDto(Member member, Address address) {
        id = member.getId();
        name = member.getName();
        nickname = member.getNickname();
        email = member.getEmail();
        privacy = member.isPrivacy();
        age = member.getAge();
        createDate = member.getCreateDate();
        lastModifiedDate = member.getLastModifiedDate();
        this.address = address;
    }

    public MemberDto(Member member, MoneyStorage moneyStorage) {
        id = member.getId();
        name = member.getName();
        nickname = member.getNickname();
        email = member.getEmail();
        privacy = member.isPrivacy();
        age = member.getAge();
        createDate = member.getCreateDate();
        storage = moneyStorage.getStoragePoint();
        lastModifiedDate = member.getLastModifiedDate();
        this.address = member.getAddress();
    }

    public MemberDto(Member saveMember) {

    }
}

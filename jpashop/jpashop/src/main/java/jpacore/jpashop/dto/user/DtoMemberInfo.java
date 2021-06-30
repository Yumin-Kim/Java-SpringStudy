package jpacore.jpashop.dto.user;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.MemberStatus;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DtoMemberInfo {
    private Address address;
    private MemberStatus memberStatus;

}

package jpacore.jpashop.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserSearchInfo {
    private DtoMemberInfo dtoMemberInfo;
    private DtoCouponMemberInfo couponMemberInfo;
    private DtoCouponInfo couponInfo;
}

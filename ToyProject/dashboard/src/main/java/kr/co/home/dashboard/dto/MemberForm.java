package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {

    public interface MemberCreateForm{}
    public interface MemberUdpateForm {}

    @NotNull(message = "name이 Null 값입니다",groups = {MemberCreateForm.class})
    @NotBlank(message = "name이 공백입니다.",groups = {MemberCreateForm.class})
    private String name;
    @Min(value = 0,message = "최소 1세 이상이여합니다",groups = {MemberUdpateForm.class,MemberCreateForm.class})
    @Max(value = 100 , message = "최대 100세 이상은 입력이 불가 합니다.",groups = {MemberUdpateForm.class,MemberCreateForm.class})
    @NotNull(message = "age이 Null 값입니다",groups = {MemberCreateForm.class})
    private Integer age;
    @Email(message = "이메일 형식이 맞지 않습니다.",groups = {MemberCreateForm.class})
    @NotNull(message = "email이 Null 값입니다",groups = {MemberCreateForm.class})
    private String email;
    @NotNull(message = "city이 Null 값입니다",groups = {MemberCreateForm.class})
    @NotBlank(message = "city이 공백입니다.",groups = {MemberCreateForm.class})
    private String city;
    @NotNull(message = "cityCode이 Null 값입니다",groups = {MemberCreateForm.class})
    @NotBlank(message = "cityCode이 공백입니다.",groups = {MemberCreateForm.class})
    private String cityCode;
    @NotNull(message = "detailCity이 Null 값입니다",groups = {MemberCreateForm.class})
    @NotBlank(message = "detailCity이 공백입니다.",groups = {MemberCreateForm.class})
    private String detailCity;

    public Member toEnity() {
        return Member.createMember(this.name, this.age, this.email, Address.createAddress(this.city, this.cityCode, this.detailCity));
    }
}

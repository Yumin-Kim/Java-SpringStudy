package jpacore.jpashop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    @NotNull(message = "도시 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String city;

    @NotNull(message = "길 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String street;

    @NotNull(message = "우편 번호 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String cityCode;

    @NotNull(message = "이름 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String name;

    @NotNull(message = "닉네임 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String nickname;

    @NotNull(message = "비밀번호 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String password;

    @NotNull(message = "이메일 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private String email;

    @NotNull(message = "입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private Boolean privacy;

    @NotNull(message = "나이 입력 필수")
    @Min(value = 0,message = "0세 이상이 여야 합니다")
    @Max(value = 100,message = "100세 이하여야 합니다")
    private Integer age;

    @NotNull(message = "직업 입력 필수")
    @NotBlank(message = "공백은 허용하지 않습니다.")
    private List<String> jobs;

    @Min(value = 0,message = "0원 이하는 지원하지 않습니다.")
    @NotNull(message = "금액 입력 필수")
    private Integer storagePrice;
}

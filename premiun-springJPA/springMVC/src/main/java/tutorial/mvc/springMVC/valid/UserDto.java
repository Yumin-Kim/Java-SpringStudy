package tutorial.mvc.springMVC.valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "이름이 입력하지 않았습니다. - NotNull")
    @NotBlank(message = "이름이 입력되어 있지 않습니다 - NotBlank")
    private String username;

    @Min(value = 0, message = "나이는 1세부터 가능합니다.")
    @Max(value = 100, message = "100세 이상의 정보는 수집하지 않습니다.")
    private Integer age;

}

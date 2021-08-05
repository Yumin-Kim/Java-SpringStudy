package tutorial.study.servlet_nolecture.mvc2.validation;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    @NotEmpty
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    private String password;
    @NotNull
    private boolean isMaster;
    @Min(0)
    private int age;
}

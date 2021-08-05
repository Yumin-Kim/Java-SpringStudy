package tutorial.study.servlet_nolecture.mvc2.validation;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean isMaster;
    private int age;

}

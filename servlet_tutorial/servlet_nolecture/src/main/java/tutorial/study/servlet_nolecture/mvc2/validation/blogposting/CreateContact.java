package tutorial.study.servlet_nolecture.mvc2.validation.blogposting;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateContact {

    @Length(max = 64)
    @NotBlank
    private String uid;

    @NotNull
    private String name;

    @Length(max = 1_600)
    private String contact;
}

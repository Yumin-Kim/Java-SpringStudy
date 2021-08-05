package tutorial.study.servlet_nolecture.mvc2.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class PersonVaildator {
    public void validator(PersonDto personDto, Errors errors) {
        if (personDto.getPassword().length() < 6) {
            errors.reject("패스워드 오류","패스워드 6자리 이상이어야한다.");
        }
        if (personDto.isMaster() && personDto.getAge() < 10) {
            errors.reject("관리자 오류" , "관리자는 나이가 10살이 넘어야");
        }
    }
}

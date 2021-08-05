package tutorial.study.servlet_nolecture.mvc2.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

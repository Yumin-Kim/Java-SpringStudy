package tutorial.study.servlet_nolecture.mvc2.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors,"name","name.empty");
        Person person = (Person) o;
        if (person.getAge() < 0) {
            errors.rejectValue("age","negativeValue");
        } else if (person.getAge() > 110) {
            errors.rejectValue("age","too.darn.old");
        }
    }
}

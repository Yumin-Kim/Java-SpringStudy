package tutorial.mvc.springMVC.valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public class UserDtoValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDto = (UserDto) o;
        if (errors.hasErrors()) {
            String field = errors.getFieldError().getField();
            log.info("Errors getField = {}" , field);
        }
    }
}

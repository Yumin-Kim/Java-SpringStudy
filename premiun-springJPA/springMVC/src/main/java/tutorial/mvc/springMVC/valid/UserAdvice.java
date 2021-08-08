package tutorial.mvc.springMVC.valid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestControllerAdvice(assignableTypes = UserController.class)
@Slf4j
public class UserAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String dtoException(BindingResult bindException) {
        log.info("userControllerAdvice.MethodArgumentNotValidException.class = {} ", bindException.getFieldError().getDefaultMessage());
        return bindException.getFieldError().getDefaultMessage();
    }

    @ExceptionHandler({BindException.class})
    public UserErrorDto userdtoError(BindingResult bindingResult) {
        log.info("userControllerAdvice.BindException.class = {} ", bindingResult.getFieldError().getDefaultMessage());
        Map<String, List<String>> collect = bindingResult.getFieldErrors().stream()
                .collect(
                        groupingBy(fieldError -> fieldError.getField(),
                                mapping(fieldErrors -> fieldErrors.getDefaultMessage(), toList()))

                );
        return new UserErrorDto(HttpStatus.OK.toString(), collect);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class UserErrorDto<T> {

        private String httpstatus;
        private T message;

    }
}

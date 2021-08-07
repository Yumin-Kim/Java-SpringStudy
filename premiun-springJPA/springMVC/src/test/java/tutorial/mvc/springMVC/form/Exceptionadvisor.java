package tutorial.mvc.springMVC.form;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class Exceptionadvisor {

    @ExceptionHandler(BindException.class)
    public List<Object> processValidationError(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder stringBuilder = new StringBuilder();

        List<Object> collect = bindingResult.getFieldErrors().stream()
                .map((fieldError) -> {
                    HashMap<String, Object> errorMap = new HashMap<>();
                    return errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList());
        return collect;
    }

    @ExceptionHandler(RuntimeException.class)
    public String runtimeException(RuntimeException runtimeException) {
        return runtimeException.getMessage();
    }
}

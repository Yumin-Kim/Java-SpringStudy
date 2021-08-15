package kr.co.home.dashboard.exception;

import kr.co.home.dashboard.dto.Res;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Res validationException(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> collect = bindingResult.getFieldErrors().stream()
                    .collect(
                            groupingBy(f -> f.getField(),
                                    mapping(fieldError -> fieldError.getDefaultMessage()
                                            , toList())));
            return Res.isError(collect);
        } else {
            return null;
        }
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public Res MemberException(Exception e){
        return Res.isError(e.getMessage());
    }

}

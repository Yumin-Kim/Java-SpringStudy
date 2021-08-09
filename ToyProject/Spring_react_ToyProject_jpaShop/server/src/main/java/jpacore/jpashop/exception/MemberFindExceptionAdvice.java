package jpacore.jpashop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@RestControllerAdvice
public class MemberFindExceptionAdvice {

    /**
     * 회원조회한 경우 존재 하지 않는 회원일때 해당 Exception이 동작 한다.
     * Not Acceptable 406 Error
     *IllegalStateException.class,RuntimeException.class,IllegalArgumentException.class
     * @param exception
     * @return
     */
    @ExceptionHandler({IllegalStateException.class,RuntimeException.class,IllegalArgumentException.class})
    public ErrorExceptionResponse stateErorrAdivice(Exception exception) {
        HashMap<String, String> resultMessage = new HashMap<>();
        resultMessage.put("message", exception.getMessage());
        return new ErrorExceptionResponse(HttpStatus.NOT_ACCEPTABLE.toString(), resultMessage);
    }

    /**
     * requeste 부분에서 @RequestBody,@ModelAttribute,@RequestBody를 통해서 Validation 수행간에 오류 발생
     * Exception을 통해 해결 하고자 한다.
     *
     * @param bindingResult
     * @return
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ErrorExceptionResponse IntegrationValidation(BindingResult bindingResult) {
        Map<String, List<String>> validationResults = bindingResult.getFieldErrors().stream()
                .collect(
                        groupingBy(fieldError -> fieldError.getField()
                                , mapping(fieldError -> fieldError.getDefaultMessage(), toList()))
                );
        return new ErrorExceptionResponse(HttpStatus.BAD_REQUEST.toString(), validationResults);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class ErrorExceptionResponse<T> {
        private String HttpStatusCode;
        private T data;
    }
}

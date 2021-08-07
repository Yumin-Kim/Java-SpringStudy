package tutorial.mvc.springMVC.valid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
public class UserController {

    @PostMapping("/user")
    public String insertUser(@Valid @RequestBody UserDto userDto) {
        log.info("@ param :{} ", userDto);
        return "success";
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String dtoException(BindException bindException){
        return bindException.getFieldError().getDefaultMessage();
    }

    // Todo ResponseEntity<?> 파일 전송에는 유용할 거 같지만 단순 JSON 전송 , 문자열 전송에는 사용하지 않는것이 유용하다
    //  HttpEntity 또한 요청에 대한 header에 대한 정보가 필요하다면 사용해도 괜찮지만 일반적인 요청 응답시에는 사용하지 않기
    @GetMapping("/users/response/entity")
    public ResponseEntity<String> responseUser(HttpEntity<String> username){
        String getUsername = username.getBody();
        return ResponseEntity.ok()
                .header(HttpHeaders.ACCEPT_RANGES)
                .body(getUsername);
    }

    @PostMapping("/user/valid/body/v1")
    public UserDto vaildationV1_Body(@Valid @RequestBody UserDto userDto){
        return userDto;
    }

    @PostMapping("/valid/query/v1")
    public Res<UserDto> vaildationV1_query(@Valid UserDto userDto){
        Res<UserDto> userDtoRes = new Res<>(userDto, 10);
        return userDtoRes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class Res<T>{
        private T data;
        private Integer id;

        public Res(T data, Integer id) {
            this.data = data;
            this.id = id;
        }
    }

}

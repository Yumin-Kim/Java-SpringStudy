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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


@Slf4j
@RestController
public class UserController {


    @InitBinder
    public void initValidation(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new UserDtoValidation());
    }

    @PostMapping("/user")
    public String insertUser(@Valid @RequestBody UserDto userDto) {
        log.info("@ param :{} ", userDto);
        return "success";
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

    //TODO
    // @ModelAttribute를 명시하면 querySttring을 받는 부분에서 문제가 없지만
    // @RequestParam을 명시하게 되면 Required request parameter "dto" .. 이런씩으로 Bad Request를 확인할 수 있게 된다.
    @GetMapping("/valid/query/v1")
    public Res<UserDto> vaildationV1_query(@Valid  UserDto userDto){
        Res<UserDto> userDtoRes = new Res<>(userDto, 10);
        return userDtoRes;
    }

    @GetMapping("/user/body/valid/v2")
    public Object validationV2_body(@Valid @RequestBody UserDto userDto , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return userDto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private static class Res<T> {
        private T data;
        private Integer id;

        public Res(T data, Integer id) {
            this.data = data;
            this.id = id;
        }
    }

}

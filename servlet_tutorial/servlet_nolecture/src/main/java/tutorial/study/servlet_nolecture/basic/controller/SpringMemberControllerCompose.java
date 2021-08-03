package tutorial.study.servlet_nolecture.basic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import tutorial.study.servlet_nolecture.basic.domain.Member;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class SpringMemberControllerCompose {

    @GetMapping("/compose/{userid}")
    public String singleParam(@PathVariable String userid) {
        return userid + "______________";
    }

    @GetMapping("/compose/{userid}/v1/{itemId}")
    public String multiParam(@PathVariable String userid, @PathVariable String itemId) {
        return userid + "______________" + itemId;
    }

    @GetMapping(value = "/dto/return")
    public Member returnDtoRoute(){
        return Member.builder()
                .username("username1")
                .age(100)
                .build();
    }

    //TODO 간단한 매핑은 Primitive 타입은 @RequestParam 애노테이션 대체할 수 있음
    // 나머지 Wrapper이나 @RequestParam이 제어 하지 못하는 멤버 변수는 @Modelattrivbute가 대체해서 만들어 주게된다.
    @GetMapping("/mapping")
    public Member getMemberDto(MemberDto memberDto){
        System.out.println("memberDto.toString() = " + memberDto.toString());
        return Member.builder()
                .username(memberDto.username)
                .age(memberDto.age)
                .build();
    }

    @PostMapping("/request-body-json-v1")
    public void requestbodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {} ", s);
        MemberDto memberDto = objectMapper.readValue(s, MemberDto.class);
        log.info("MemberDto = {}",memberDto.toString());
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV2(@RequestBody String messageBy) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("messageBody = {} ", messageBy);
        MemberDto memberDto = objectMapper.readValue(messageBy, MemberDto.class);
        log.info("MemberDto = {}", memberDto);
        return "ok";
    }

    // Todo @ResponseBody는 view조회를 무시하고 HTTP message body에 직접 해당 내용을 입력
//    @PostMapping("/request-body-json-v3")
//    @ResponseBody
//    public String requestBodyJsonV4(){
//
//    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    private static class MemberDto {
        private int age;
        private String username;
        private String address;
    }
}

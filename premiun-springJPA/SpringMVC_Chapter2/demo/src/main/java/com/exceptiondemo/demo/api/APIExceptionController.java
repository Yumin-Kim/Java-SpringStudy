package com.exceptiondemo.demo.api;

import com.exceptiondemo.demo.exception.MemberException;
import com.exceptiondemo.demo.exception.NewMemberException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class APIExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new MemberException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못도니 입력 입니다.");
        }
        return new MemberDto(id, "helllo " + id);
    }

    @GetMapping("/api/exception")
    public String getNewMemeberException() {
        throw new NewMemberException();
    }

    @GetMapping("/api/exception-ex2")
    public String getResponseStatusException(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ResponseStatusException 발생", new IllegalArgumentException());
    }

    @GetMapping("/api/exception-ex-handler")
    public String getDefaultException(@RequestBody Integer data) {
        return "ok";
    }



    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    private class MemberDto {
        private String memberId;
        private String name;
    }

}

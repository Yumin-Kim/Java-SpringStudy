package com.exceptiondemo.demo.servlet;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외 발생");
    }

    @GetMapping("/error-ex/child")
    @SneakyThrows(value = ChildException.class)
    public void getExceptionChildClass() {
        throw new ChildException("자식 예외 발생");
    }

    @GetMapping("/error-404")
    public void getError404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404오류");
    }

    @GetMapping("/error-500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void getError500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }

    private class ChildException extends Exception {
        public ChildException(String message) {
            super(message);
        }
    }
}

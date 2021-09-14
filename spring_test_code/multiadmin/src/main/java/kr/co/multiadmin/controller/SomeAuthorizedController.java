package kr.co.multiadmin.controller;

import kr.co.multiadmin.service.SomeAuthorizedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SomeAuthorizedController {


    private final SomeAuthorizedService someAuthorizedService;

    @GetMapping("/general")
    public String general() {
        log.info("in the general");
        return "ok";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("in the admin");
        return "ok";
    }

    @GetMapping("/anonymous-service")
    public String anonymousService(){
        someAuthorizedService.anonymousCanAccess();
        return "ok";
    }

}

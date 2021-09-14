package kr.co.multiadmin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class HelloAPIController {

    @GetMapping
    public String helloMember(){
        return "hello";
    }

}

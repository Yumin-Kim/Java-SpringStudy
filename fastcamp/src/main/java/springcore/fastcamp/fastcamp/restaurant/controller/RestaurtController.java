package springcore.fastcamp.fastcamp.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurtController {
    @GetMapping("/")
    public String hello(){
        return "Hello world";
    }
}

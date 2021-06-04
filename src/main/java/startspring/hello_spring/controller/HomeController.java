package startspring.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import startspring.hello_spring.domain.Member;

@Controller
public class HomeController {



    @GetMapping("/")
    public String home() {
        return "home";
    }


}

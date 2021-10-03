package kr.co.spring11java;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/")
    public String getHello(Model model) {
        model.addAttribute("data", "Hello Spring");
        return "hello";
    }

}

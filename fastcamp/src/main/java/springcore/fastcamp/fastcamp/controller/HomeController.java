package springcore.fastcamp.fastcamp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springcore.fastcamp.fastcamp.security.UserDetailsImpl;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("admin", true);
        return "index";
    }
}

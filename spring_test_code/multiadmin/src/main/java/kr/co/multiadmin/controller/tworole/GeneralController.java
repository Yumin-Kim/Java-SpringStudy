package kr.co.multiadmin.controller.tworole;

import kr.co.multiadmin.repository.GeneralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/general")
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralRepository generalRepository;

    @PostMapping
    public String getGeneral() {

        return "general";
    }

    @GetMapping("/list")
    public String getGeneralList() {
        return "generalList";
    }

    @GetMapping("/login")
    public String generalLogin() {
        return "general_login";
    }
}

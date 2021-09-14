package kr.co.multiadmin.controller;

import kr.co.multiadmin.AccountDto;
import kr.co.multiadmin.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterationController {

    private final AccountService accountService;

    @GetMapping("/register")
    public String getRegister(@ModelAttribute AccountDto.RegisterRequest request) {
        accountService.createAccount(request);
        log.info("user created");
        return "redirect:/";
    }

}

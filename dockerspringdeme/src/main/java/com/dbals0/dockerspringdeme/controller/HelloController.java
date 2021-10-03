package com.dbals0.dockerspringdeme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping
    public String getHello(Model model) {
        model.addAttribute("data", "Docker compose spring");
        return "hello";
    }

    @GetMapping("/{text}")
    public String getText(Model model, @PathVariable String text) {
        model.addAttribute("data", text);
        return "hello";
    }
}

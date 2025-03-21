package org.mj.audio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("/")
    public String defaultRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String home() {
        return "login/loginPage";
    }
}

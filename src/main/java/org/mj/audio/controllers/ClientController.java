package org.mj.audio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @GetMapping
    public String index() {
        return "redirect:client/home";
    }

    @GetMapping("/home")
    public String home() {
        return "client/home";
    }
}
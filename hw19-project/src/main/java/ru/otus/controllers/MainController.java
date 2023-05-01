package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String toHomepage() {
        return "index";
    }

//    @GetMapping("/login")
//    public String toLoginPage() {
//        return "login";
//    }
}

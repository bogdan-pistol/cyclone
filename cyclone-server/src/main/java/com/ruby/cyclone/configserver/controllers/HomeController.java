package com.ruby.cyclone.configserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "index.html";
    }

}

package com.example.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/hello/calendar")
    public String showHello(Principal principal) {
        return "/hello";
    }
}

package com.tin.PokerHeadsUp.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "Welcome to Pokercs";
    }

    @GetMapping("api")
    public String testSecure() {
        return "Welcome to testSecure";
    }
}

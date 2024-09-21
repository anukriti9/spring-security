package com.example.SpringSecurity6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/demo1")
    public String demo1() {
        return "Demo1!";
    }

    @PostMapping("/demo2")
    public String demo2() {
        return "Demo2!";
    }

}

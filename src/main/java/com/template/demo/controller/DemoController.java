package com.template.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DemoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/api/test")
    String test() {
        return "Hello world !!!!!!!!!!";
    }
    @GetMapping("time/now")
    String now() {
        return new Date().toLocaleString();
    }
}

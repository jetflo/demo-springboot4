package com.jetdev.demospringboot4.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/false")
public class FalseController {

    //@GetMapping(version = "1.3")
    public void getFalse() {
    }
}

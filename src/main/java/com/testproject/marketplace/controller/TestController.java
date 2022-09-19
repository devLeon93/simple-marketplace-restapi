package com.testproject.marketplace.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    /*@PreAuthorize("hasRole('USER')")*/
    @GetMapping
    public String sayHello(){
        return "Hello Leonid";
    }

}

package com.techtips.uzbekTechTips.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtips.uzbekTechTips.model.Users;
import com.techtips.uzbekTechTips.services.UserService;

@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody Users user){
        return userService.register(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }

    @GetMapping("deleteAll")
    public String deleteAll(){
        return userService.deleteAll();
    }

    @GetMapping("isAuthenticated")
    public boolean isAuthenticated(){
        return true;
    }
}

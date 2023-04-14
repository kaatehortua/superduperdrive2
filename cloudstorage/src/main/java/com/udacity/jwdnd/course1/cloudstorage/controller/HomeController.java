package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserMapper userMapper;
    private final UserService userService;


    public HomeController(UserMapper userMapper, UserService userService) {
        this.userService= userService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model){
        User usuLog=userService.getUser(auth.getName());
        int userid=usuLog.getId();


        return "home";
    }
}

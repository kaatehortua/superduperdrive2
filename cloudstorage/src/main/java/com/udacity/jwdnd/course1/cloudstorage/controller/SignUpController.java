package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    public final UserService userService;
    public SignUpController(UserService userService){
        this.userService=userService;
    }
    @GetMapping()
    public String signup(){
        return "signup";
    }
    @PostMapping()
    public String register(@ModelAttribute User user, Model model){
        String error=null;

        if(!userService.avaliable(user.getUsername())){
            error=" User name no avaliable";
        }
        if(error==null){
            int row=0;
            row=userService.createUser(user);
            if (row<0){
                error="error with register";
            }

        }
        if(error== null){

            model.addAttribute("signupSuccess",true);
        }else {

            model.addAttribute("signupError",error);
        }



        return "signup";
    }

}

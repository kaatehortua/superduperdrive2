package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/signup")
public class RegisterController {
    private UserService service;

    public RegisterController(UserService service) {
        this.service = service;
    }
    @GetMapping()
    public String registermapString(){
        return "signup";
    }
    @PostMapping()
    public String registerControl(@ModelAttribute UserModel user, Model model, RedirectAttributes redirect) throws Exception {
        String errorControl="";
    try {


        if (!service.checkUserName(user.getUsername())) {
            errorControl = "user name exist";
        }else{
        if (errorControl.equals("")) {
            int isadd = 0;
            isadd = service.insertUser(user);
            if (isadd < 0) {
                errorControl = "error signup";
            }
            if (errorControl.equals("")) {
                redirect.addFlashAttribute("redirectOK", true);
                return "redirect:/login";
            } else {
                model.addAttribute("registerError", errorControl);
            }
        }
        }
    }catch (Exception e){
        System.out.println("error: " + e);
    }
        return "signup";
    }
}

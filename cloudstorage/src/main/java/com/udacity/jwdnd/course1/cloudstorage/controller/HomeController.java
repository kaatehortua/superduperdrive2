package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
   private CredentialService credentialService;
   private UserService userService;
   private UserMapper mapper;
   private EncryptionService encrypt;

    public HomeController(CredentialService credentialService, UserService userService, UserMapper mapper, EncryptionService encrypt) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.mapper = mapper;
        this.encrypt = encrypt;
    }
    @GetMapping("/home")
    public String home(Authentication auth, Model model){
        String logged= (String) auth.getPrincipal();
        UserModel user= mapper.getUser(logged);

        model.addAttribute("credentials",credentialService.getListCredential(user.getUserId()));
        model.addAttribute("encryption",encrypt);
        return "home";
    }
    @GetMapping("/result")
    public String result(){return "result";}
}

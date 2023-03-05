package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/home/credential")
@Controller
public class CredentialController {
    private CredentialService service;
    private UserMapper mapper;
    boolean confirm=false;
    int deleted=0;
    public CredentialController(){

    }
    public CredentialController(CredentialService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    @PostMapping
    public String CredentialMannager(Authentication auth, CredentialModel cred){
        String logged=(String) auth.getPrincipal();
        UserModel user= mapper.getUser(logged);
        int id=user.getUserId();
        Integer credid=(Integer) cred.getId();
       if (credid==null){
           service.insertCredentials(cred,id);
       }else{
         this.confirm=  service.updateCredential(cred);
       }
       if (confirm==true){
           return "redirect:/result?success";
       }else {
           return "redirect:/result?error";
       }
    }
    @GetMapping("/delete")
    public String deleteCred(@RequestParam("id") int id, Authentication auth, RedirectAttributes redirect){
        String logged=(String) auth.getPrincipal();
        UserModel user= mapper.getUser(logged);

        if(id != 0 && id>0){
            deleted= service.deleteCred(id);
        }
        if (deleted>0){
            return "redirect:/result?success";
        }else{
            redirect.addAttribute("error", "Problem while deleting credentials");
            return "redirect:/result?error";
        }
    }
}

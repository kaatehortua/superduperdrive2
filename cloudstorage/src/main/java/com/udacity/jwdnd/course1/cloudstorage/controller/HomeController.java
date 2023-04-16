package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserMapper userMapper;
    private final UserService userService;

    private final NoteService noteService;


    public HomeController(UserMapper userMapper, UserService userService,NoteService noteService) {
        this.userService= userService;
        this.userMapper = userMapper;
        this.noteService=noteService;
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model, @ModelAttribute("notesF") Note note){
        User usuLog=userService.getAuth(auth);
        Integer userid=usuLog.getId();
        model.addAttribute("notes", noteService.getAll(userid));


        return "home";
    }
}

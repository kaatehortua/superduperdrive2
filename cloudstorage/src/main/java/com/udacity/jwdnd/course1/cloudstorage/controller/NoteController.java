package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
private final NoteService noteService;
private final UserService userService;



    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/delete/{id}")
    public String delete(Authentication authentication, @PathVariable("id") Integer id, Model model) throws Exception{

           try {

               noteService.deleteNote(id);
               model.addAttribute("success", true);
               model.addAttribute("successMes", "Se ha eliminado la nota");
                return "home";

           } catch (Exception e) {
               model.addAttribute("error", true);
               model.addAttribute("errorMes", "Se ha eliminado la nota");
           }
           model.addAttribute("notes", this.noteService.getAll(userService.getUser(authentication.getName()).getId()));

       return "home";

    }

    @PostMapping("/add")
    public String addNote(Authentication authentication, @ModelAttribute("notesF") Note note, Model model){
        String userName= (String) authentication.getPrincipal();
        User user = userService.getUser(userName);
        note.setUserId(user.getId());
        Integer validate= note.getId();
        if(validate!=null){
            noteService.updateNote(note.getId(),note.getTitle(),note.getDescr());
            model.addAttribute("success", true);
            model.addAttribute("successMes","se actualizo la nota correctamente");

        }else {
            try {

                noteService.insertNote(note);
                model.addAttribute("success", true);
                model.addAttribute("successMes", "se inserto la nota correctamente");


            } catch (Exception e) {
                model.addAttribute("error", true);
                model.addAttribute("errorMes", "Se ha generado un error al agregar o actualizar la nota");
                System.out.println(e);

            }
        }

        model.addAttribute("notes",this.noteService.getAll(userService.getUser(authentication.getName()).getId()));

        return "home";
    }

}

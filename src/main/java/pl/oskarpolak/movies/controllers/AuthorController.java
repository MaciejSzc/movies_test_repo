package pl.oskarpolak.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.oskarpolak.movies.models.services.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/author/all")
    public String all(Model model){
        model.addAttribute("authors", authorService.getAuthors());
        return "author_all";
    }

}


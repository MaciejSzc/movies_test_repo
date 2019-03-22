package pl.oskarpolak.movies.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.oskarpolak.movies.models.forms.LoginForm;
import pl.oskarpolak.movies.models.forms.RegisterForm;
import pl.oskarpolak.movies.models.services.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "user_login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                           Model model){
        if(!userService.login(loginForm)){
            //logowanie nie udane
            model.addAttribute("info", "Login or password incorrect");
            return "user_login";
        }
        //logowanie udane
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "user_register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm,
                           Model model){
        if(!userService.register(registerForm)){
            model.addAttribute("info", "Username is already taken");
            return "user_register";
        }

        //todo udalo sie
        return "redirect:/login";
    }


}

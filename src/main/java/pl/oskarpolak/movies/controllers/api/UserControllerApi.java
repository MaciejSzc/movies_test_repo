package pl.oskarpolak.movies.controllers.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.oskarpolak.movies.models.services.UserService;

@RestController
@RequestMapping("/api")
public class UserControllerApi {

    final UserService userService;

    @Autowired
    public UserControllerApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }
}

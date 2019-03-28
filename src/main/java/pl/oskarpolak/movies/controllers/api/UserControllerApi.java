package pl.oskarpolak.movies.controllers.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.movies.models.entities.UserEntity;
import pl.oskarpolak.movies.models.forms.RegisterForm;
import pl.oskarpolak.movies.models.services.UserService;

@RestController
@RequestMapping("/api")
public class UserControllerApi {

    final UserService userService;

    @Value("${api.key}")
    String restKey;


    @Autowired
    public UserControllerApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity getOneUser(@PathVariable("id") int id){
        UserEntity user = userService.findUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity softDeleteUserById(@PathVariable("id") int id,
                                             @RequestHeader("api-key") String apiKey){
        if(!restKey.equals(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad api key");
        }

        userService.softDeleteUserById(id);
        return ResponseEntity.ok("deleted " + id);
    }

    @PostMapping(value = "/user", produces = "application/json", consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterForm registerForm){
        if(!userService.register(registerForm)){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is taken");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

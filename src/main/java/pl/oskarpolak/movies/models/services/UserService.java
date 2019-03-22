package pl.oskarpolak.movies.models.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.movies.models.entities.UserEntity;
import pl.oskarpolak.movies.models.forms.LoginForm;
import pl.oskarpolak.movies.models.forms.RegisterForm;
import pl.oskarpolak.movies.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSession userSession;

    //jak będzie wygladac metoda logujaca?
    public boolean login(LoginForm loginForm){
        Optional<UserEntity> user = userRepository.findUserByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());
        if(user.isPresent()){
            userSession.setLogin(true);
            userSession.setUserId(user.get().getId());
            return true;
        }

        return false;
    }

    public boolean register(RegisterForm registerForm) {
        if(userRepository.isUsernameTaken(registerForm.getLogin())){
            return false;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registerForm.getPassword());
        userEntity.setUsername(registerForm.getLogin());
        userEntity.setEmail(registerForm.getMail());


        userRepository.save(userEntity);
        return true;
    }
}

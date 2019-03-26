package pl.oskarpolak.movies.models.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        UserEntity user = userRepository.findUserByUsername(loginForm.getLogin());
        if(user != null && getBCrypt().matches(loginForm.getPassword(), user.getPassword())){
            userSession.setLogin(true);
            userSession.setUserId(user.getId());
            return true;
        }

        return false;
    }

    public boolean register(RegisterForm registerForm) {
        if(userRepository.isUsernameTaken(registerForm.getLogin())){
            return false;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(getBCrypt().encode(registerForm.getPassword()));
        userEntity.setUsername(registerForm.getLogin());
        userEntity.setEmail(registerForm.getMail());


        userRepository.save(userEntity);
        return true;
    }


    @Bean
    public BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }

    public Iterable<UserEntity> getAll(){
        return userRepository.findAll();
    }


    public UserEntity findUserById(int id){
        return userRepository.findById(id).get();
    }

//    public Optional<UserEntity> getLoginUser(){
//        return userRepository.findById(userSession.getUserId());
//    }
}

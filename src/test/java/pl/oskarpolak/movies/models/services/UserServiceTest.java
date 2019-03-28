package pl.oskarpolak.movies.models.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.oskarpolak.movies.models.entities.UserEntity;
import pl.oskarpolak.movies.models.forms.LoginForm;
import pl.oskarpolak.movies.models.forms.RegisterForm;
import pl.oskarpolak.movies.models.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserServiceTest {

    //@Autowired // przywolanie normalne serwisu!

    @Mock
    UserRepository userRepository;

    @Mock
    UserSession userSession;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldLoginUser() {
        //given
        String testPasswordHash = "$2a$10$vOTTj1O7fjCKlAhV8CRjY./IPBdyUjMm3FlTay.3TqDPwqUY6u9PO";

        LoginForm loginForm  = new LoginForm();
        loginForm.setLogin("test");
        loginForm.setPassword("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword(testPasswordHash);

        //when
        Mockito.when(userRepository.findUserByUsername("test")).thenReturn(userEntity);


        //then
        Assertions.assertTrue(userService.login(loginForm));
    }

    @Test
    void shouldNotRegisterAlreadyTakenUsername(){
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setLogin("test");
        registerForm.setMail("test");
        registerForm.setPassword("test");

        //when
        Mockito.when(userRepository.isUsernameTaken("test")).thenReturn(true);

        //then
        Assertions.assertFalse(userService.register(registerForm));
    }
}
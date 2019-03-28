package pl.oskarpolak.movies.controllers.api;

import com.google.gson.Gson;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.oskarpolak.movies.models.forms.RegisterForm;
import pl.oskarpolak.movies.models.services.UserService;

import javax.management.MXBean;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerApiTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserControllerApi userControllerApi;

    @Autowired
    MockMvc mockMvc;


    @Test
    void shouldAddUser() throws Exception {
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setPassword("abc");
        registerForm.setLogin("asd");
        registerForm.setMail("asdasd");

        //when
        Mockito.when(userService.register(registerForm)).thenReturn(true);

        //then
        mockMvc.perform(post("/api/user")
                        .content(getGson().toJson(registerForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


      @Test
    void shouldDeleteUser() throws Exception {
        //when
        int idToDelete = 5;

        //when
        doAnswer(s -> null).when(userService).findUserById(idToDelete);

        //given

        mockMvc.perform(delete("/api/user/" + idToDelete)
                        .header("api-key", "adas8askfaidADYGDYAGD98753nwDNSAAUFHWQ9FDsaf"))
                .andExpect(status().isOk());
    }


    public Gson getGson(){
        return new Gson();
    }
}
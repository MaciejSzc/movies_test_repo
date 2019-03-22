package pl.oskarpolak.movies.models.forms;

import lombok.Data;

@Data
public class RegisterForm {
    private String login;
    private String password;
    private String mail;
}

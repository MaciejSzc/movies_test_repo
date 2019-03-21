package pl.oskarpolak.movies.models.forms;

import lombok.Data;

@Data
public class LoginForm {
    private String login;
    private String password;
}

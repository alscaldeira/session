package com.caldeira.controller.dto;

import com.caldeira.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {

    public LoginDTO() { }

    public LoginDTO(User user) {
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }

    private String email;
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


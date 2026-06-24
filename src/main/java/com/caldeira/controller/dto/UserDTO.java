package com.caldeira.controller.dto;

import com.caldeira.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserDTO {
    private String password;
    private String email;

    public UserDTO() { }

    public UserDTO(User user) {
        this.setEmail(user.getEmail());
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}

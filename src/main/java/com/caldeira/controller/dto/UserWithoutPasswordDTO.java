package com.caldeira.controller.dto;

public class UserWithoutPasswordDTO {
    private String email;

    public UserWithoutPasswordDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

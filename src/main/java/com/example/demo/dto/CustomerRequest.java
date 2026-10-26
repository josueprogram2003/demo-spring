package com.example.demo.dto;

import static com.example.demo.dto.DtoValidations.requireText;

public record CustomerRequest(String name, String lastName, String email) {

    public CustomerRequest {
        requireText(name, "name");
        requireText(lastName, "lastName");
        requireText(email, "email");
    }
}

package com.dnxo.todoxo.service.dto.user;

import lombok.Data;

@Data
public class CreateUserDto {
    private String email;
    private String username;
    private String password;
}

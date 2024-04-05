package com.dnxo.todoxo.service.dto.user;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    private int id;
    private String email;
    private String newPassword;
    private String oldPassword;
}

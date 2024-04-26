package com.dnxo.todoxo.persistence.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleDTO {
    private String username;
    private String role;
    private LocalDateTime grantedDate;
}

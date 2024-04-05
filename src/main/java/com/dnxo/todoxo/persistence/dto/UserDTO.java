package com.dnxo.todoxo.persistence.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String username;
    private List<TaskDTO> tasks;
}

package com.dnxo.todoxo.service.dto;

import lombok.Data;

@Data
public class UpdateTaskNameDto {
    private int taskId;
    private int userId;
    private String newName;
}

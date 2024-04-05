package com.dnxo.todoxo.service.dto.task;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
    private int taskId;
    private int userId;
    private String description;
    private LocalDateTime date;
    private boolean state;
}

package com.dnxo.todoxo.persistence.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private int taskId;
    private int userId;
    private String description;
    private LocalDateTime date;
    private boolean state;
}
